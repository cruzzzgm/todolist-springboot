package br.com.snowproject.todolist.task;

import br.com.snowproject.todolist.filter.utils.Utils;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO create(TaskRequestDTO taskDTO, UUID idUser) {
        var taskModel = new TaskModel();
        Utils.copyNonNullProperties(taskDTO, taskModel);
        taskModel.setIdUser(idUser);
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            throw new IllegalArgumentException("A data de início deve ser menor que a data de término.");
        }

        var taskSaved = this.taskRepository.save(taskModel);
        return new TaskResponseDTO(taskSaved);
    }

    public List<TaskModel> listByUser(UUID idUser) {
        return this.taskRepository.findByIdUser(idUser);
    }

    public TaskResponseDTO update(TaskRequestDTO taskDTO, UUID taskId, UUID idUser) {
        TaskModel task = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        if (!task.getIdUser().equals(idUser)) {
            throw new SecurityException("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(taskDTO, task);

        if (task.getStartAt().isAfter(task.getEndAt())) {
            throw new IllegalArgumentException("A data de início deve ser menor que a data de término.");
        }

        var taskUpdated = this.taskRepository.save(task);
        return new TaskResponseDTO(taskUpdated);
    }
}