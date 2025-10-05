package br.com.snowproject.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class TaskResponseDTO {

    private UUID id;
    private UUID idUser;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private LocalDateTime createdAt;

    public TaskResponseDTO(TaskModel task) {
        this.id = task.getId();
        this.idUser = task.getIdUser();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.startAt = task.getStartAt();
        this.endAt = task.getEndAt();
        this.priority = task.getPriority();
        this.createdAt = task.getCreatedAt();
    }
}