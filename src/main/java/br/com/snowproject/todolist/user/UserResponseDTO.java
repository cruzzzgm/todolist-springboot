package br.com.snowproject.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserResponseDTO {

    private UUID id;
    private String username;
    private String name;
    private LocalDateTime createdAt;

    public UserResponseDTO(UserModel user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.createdAt = user.getCreatedAt();
    }
}