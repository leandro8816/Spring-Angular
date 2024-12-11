package com.event.demo.evento;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(Event event) {

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getEventById(id);
        event.setName(eventDetails.getName());
        event.setDescription(eventDetails.getDescription());
        event.setDateTime(eventDetails.getDateTime());
        event.setLocation(eventDetails.getLocation());
        event.setOrganizer(eventDetails.getOrganizer());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

}
