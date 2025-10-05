package br.com.snowproject.todolist.task;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 50, message = "O título deve conter no máximo 50 caracteres")
    private String title;

    private String description;

    @Future(message = "A data de início deve ser uma data futura.")
    private LocalDateTime startAt;

    @Future(message = "A data de término deve ser uma data futura.")
    private LocalDateTime endAt;

    private String priority;
}