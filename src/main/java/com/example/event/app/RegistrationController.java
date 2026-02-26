package com.example.event.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    // Show registration form
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle form submission
    @PostMapping
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            userRepository.save(user);
            // flash success message
            redirectAttributes.addFlashAttribute("successMessage", "🎉 You have successfully registered!");
            return "redirect:/categories"; // must have mapping for /categories
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "⚠️ This phone/email is already registered!");
            return "redirect:/register"; // reload form with error
        }
    }
}
