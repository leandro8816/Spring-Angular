package com.event.demo.evento;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String datetime;
    private String location;
    private String organizer;
    private String image; // Representação Base64 da imagem

    // Getters e Setters
}
