package com.event.demo.user;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers() {

        List<UserDto> users = service.listUsers()
                .stream()
                .map(usr -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(usr.getId());
                    userDto.setFirstname(usr.getFirstname());
                    userDto.setLastname(usr.getLastname());
                    userDto.setEmail(usr.getEmail());

                    if (usr.getImage() != null) {
                        userDto.setImage("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(usr.getImage()));
                    }

                    return userDto;  // retorna o UserDto, não ResponseEntity
                }).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
    

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestBody UserResponse request
    ) {
        service.updateUser(id, request);
        return ResponseEntity.ok().build();
    }

    // Endpoint para apagar um utilizador
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
