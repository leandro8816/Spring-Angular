package com.event.demo.evento;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEventRequest {

    private String name;
    private String description;
    private String dateTime;
    private String location;
    private String organizer;
    @Getter
    private MultipartFile image;

}
