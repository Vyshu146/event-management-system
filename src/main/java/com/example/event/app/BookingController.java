package com.example.event.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    // Show all bookings
    @GetMapping
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "bookings-list";
    }

    // Form to create a new booking
    @GetMapping("/new/{eventId}")
    public String newBookingForm(@PathVariable Long eventId, Model model) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event == null || event.getAvailableSeats() <= 0) {
            model.addAttribute("errorMessage", "Sorry, no seats available for this event.");
            return "error";
        }

        Booking booking = new Booking();
        booking.setEvent(event);
        model.addAttribute("booking", booking);
        return "booking-form";
    }

    // Save booking with notification and success message
    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking, RedirectAttributes redirectAttributes) {
        Event event = eventRepository.findById(booking.getEvent().getId()).orElse(null);

        if (event != null && event.getAvailableSeats() > 0) {
            // reduce available seats
            event.setAvailableSeats(event.getAvailableSeats() - 1);
            eventRepository.save(event);

            // save booking
            bookingRepository.save(booking);

            // create notification
            String message = "🎉 You successfully booked '" + event.getName() + 
                             "'. Your ticket is ready! Enjoy the show!";
            Notification notification = new Notification(message);
            notificationRepository.save(notification);

            // flash message for user
            redirectAttributes.addFlashAttribute("successMessage", message);
        }

        return "redirect:/events"; // user will see success message on events page
    }
}
