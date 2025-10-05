package br.com.snowproject.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TaskRequestDTO taskDTO, HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("idUser");
        var taskResponse = this.taskService.create(taskDTO, idUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> list(HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("idUser");
        var tasks = this.taskService.listByUser(idUser);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TaskRequestDTO taskDTO, @PathVariable UUID id,
            HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("idUser");
        var taskResponse = this.taskService.update(taskDTO, id, idUser);
        return ResponseEntity.ok(taskResponse);
    }
}