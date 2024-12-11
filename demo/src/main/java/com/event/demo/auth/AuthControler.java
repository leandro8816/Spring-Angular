package com.event.demo.auth;

import com.event.demo.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthControler {

    private final AuthenticationService service;
    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws IOException {
        byte[] imageBytes = null;
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setRole(request.getRole());
        if (request.getImage() != null) {
            user.setImage(request.getImage().getBytes());
        } else {
            try (InputStream inputStream = new ClassPathResource("img/usr.jpg").getInputStream()) {
                imageBytes = inputStream.readAllBytes();
                System.out.println(imageBytes.length);
            }

        }
        user.setImage(imageBytes);
        return ResponseEntity.ok( service.register(user));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }



}
