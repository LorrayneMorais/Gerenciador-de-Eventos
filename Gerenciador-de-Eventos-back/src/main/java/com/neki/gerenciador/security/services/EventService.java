package com.neki.gerenciador.security.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.gerenciador.dto.EventDto;
import com.neki.gerenciador.security.entities.Admin;
import com.neki.gerenciador.security.entities.Event;
import com.neki.gerenciador.security.repositories.AdminRepository;
import com.neki.gerenciador.security.repositories.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Event> getEventsByAdmin(Long adminId) {
        return eventRepository.findByAdminId(adminId);
    }

    public Event addEvent(EventDto event) {
        Event newEvent = new Event();
       
        Admin admin = adminRepository.findById(event.getAdminId())
            .orElseThrow(() -> new RuntimeException("Admin not found"));

        newEvent.setAdmin(admin);
        newEvent.setDate(event.getDataEvento());
        newEvent.setLocation(event.getLocation());
        newEvent.setImage(event.getImagem());
        newEvent.setName(event.getNomeEvento());

        return eventRepository.save(newEvent);
    }

    public Event updateEvent(Long eventId, LocalDate newDate, String newLocation) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setDate(newDate);
        event.setLocation(newLocation);
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
