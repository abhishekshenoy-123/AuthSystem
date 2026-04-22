package com.AuthSystem.AuthSystem.controller;

import com.AuthSystem.AuthSystem.model.User;
import com.AuthSystem.AuthSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "index"; // points to index.html
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // points to register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Hashing should be implemented in production
        newUser.setRole("USER");
        userRepository.save(newUser);
        
        return "redirect:/?registered=true";
    }

    @GetMapping("/success")
    public String success(Authentication authentication, Model model) {
        if (authentication != null && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = oauth2User.getAttribute("login");

            if (username != null) {
                Optional<User> existingUser = userRepository.findByUsername(username);
                if (existingUser.isEmpty()) {
                    User newUser = new User();
                    newUser.setUsername(username);
                    // generate a dummy password for Github users
                    newUser.setPassword(UUID.randomUUID().toString()); 
                    newUser.setRole("OAUTH_USER");
                    userRepository.save(newUser);
                }
                model.addAttribute("username", username);
            }
        }
        return "success";
    }
}
