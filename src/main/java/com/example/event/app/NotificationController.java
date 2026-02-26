package com.example.event.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // Show all notifications
    @GetMapping("/notifications")
    public String viewNotifications(Model model) {
        model.addAttribute("notifications", notificationRepository.findAll());
        return "notifications"; // maps to notifications.html
    }

    // Add a sample notification (for testing)
    @PostMapping("/notifications/add")
    public String addSampleNotification() {
        notificationRepository.save(new Notification("🎉 New event added! Don’t miss it."));
        return "redirect:/notifications";
    }
}
