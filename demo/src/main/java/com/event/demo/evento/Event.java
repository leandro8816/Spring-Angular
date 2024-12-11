package com.event.demo.evento;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String dateTime;
    private String location;
    private String organizer;

    @Lob
    private byte[] image;


}
