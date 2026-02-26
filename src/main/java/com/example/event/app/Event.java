package com.example.event.app;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;

    @Column(name = "date_time")
    private LocalDateTime date_time;

    @Column(name = "availableseats")
    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // new field

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getDateTime() { return date_time; }
    public void setDateTime(LocalDateTime dateTime) { this.date_time = dateTime; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
