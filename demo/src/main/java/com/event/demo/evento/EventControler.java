package com.event.demo.evento;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@CrossOrigin
public class EventControler {

    private final EventService eventService;

    /*@PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody RegisterEventRequest event) {
        try {
            byte[] imageBytes = event.getImage().getBytes();
            return ResponseEntity.ok(eventService.createEvent(event, imageBytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createEvent(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("datetime") String datetime,
            @RequestPart("location") String location,
            @RequestPart("organizer") String organizer,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            // Crie um objeto Event ou similar com os dados recebidos
            Event event = new Event();
            event.setName(name);
            event.setDescription(description);
            event.setDateTime(datetime);
            event.setLocation(location);
            event.setOrganizer(organizer);
            event.setImage(image.getBytes()); // Ou salve o arquivo conforme sua lógica

            // Salve o evento usando o service
            eventService.createEvent(event);

            return ResponseEntity.status(HttpStatus.CREATED).body("Evento criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar evento: " + e.getMessage());
        }
    }

    /*@GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        return ResponseEntity.ok(eventService.getAllEvents());
    }*/

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents()
                .stream()
                .map(event -> {
                    EventDTO dto = new EventDTO();
                    dto.setId(event.getId());
                    dto.setName(event.getName());
                    dto.setDescription(event.getDescription());
                    dto.setDatetime(event.getDateTime());
                    dto.setLocation(event.getLocation());
                    dto.setOrganizer(event.getOrganizer());

                    // Converte a imagem para Base64 e adiciona ao DTO
                    if (event.getImage() != null) {
                        dto.setImage("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(event.getImage()));
                        System.out.println(dto.getImage());
                    }

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@RequestParam Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
