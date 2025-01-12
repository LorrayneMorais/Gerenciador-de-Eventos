package com.neki.gerenciador.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neki.gerenciador.dto.EventDto;
import com.neki.gerenciador.security.entities.Event;
import com.neki.gerenciador.security.services.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/{adminId}")
    public List<Event> getEventsByAdmin(@PathVariable Long adminId) {
        return eventService.getEventsByAdmin(adminId);
    }

    @PostMapping
    public Event addEvent(@RequestBody EventDto event) {
        return eventService.addEvent(event);
    }

    @PutMapping("/{eventId}")
    public Event updateEvent(
            @PathVariable Long eventId,
            @RequestParam String date,
            @RequestParam String location
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return eventService.updateEvent(eventId, parsedDate, location);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format. Expected format: yyyy-MM-dd'T'HH:mm:ss");
        }
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }
}