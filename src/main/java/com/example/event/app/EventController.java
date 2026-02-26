package com.example.event.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Show all events
    @GetMapping
    public String listEvents(Model model) {
         model.addAttribute("events", eventRepository.findAll());
        return "events";  // looks for events.html
    }

    // Show events by category
    @GetMapping("/category/{id}")
    public String eventsByCategory(@PathVariable Long id, Model model) {
        List<Event> events = eventRepository.findByCategoryId(id);
        model.addAttribute("events", events);
        return "events";  // reuse same template
    }


    // Show all categories
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories"; // categories.html page
    }

    // Show single event details
    @GetMapping("/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id).orElse(null);
        model.addAttribute("event", event);
        return "event-details"; // you can make a separate details page
    }
}        

