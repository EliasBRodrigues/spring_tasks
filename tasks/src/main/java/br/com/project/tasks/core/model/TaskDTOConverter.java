package br.com.project.tasks.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.project.tasks.core.Task;

@Component
public class TaskDTOConverter {

    // transform Model -> DTO
    public TaskDTO convert(Task task) {
        return Optional.ofNullable(task)
                .map(source -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setTitle(source.getTitle());
                    taskDTO.setDescription(source.getDescription());
                    taskDTO.setPriority(source.getPriority());
                    taskDTO.setState(source.getState());
                    return taskDTO;
                }).orElse(null);
    }

    // transform DTO -> Model
    public Task convert(TaskDTO taskDTO) {
        return Optional.ofNullable(taskDTO)
                .map(source -> Task.builder()
                .withTitle(source.getTitle())
                .withDescription(source.getDescription())
                .withPriority(source.getPriority())
                .withTaskState(source.getState())
                .build())
                .orElse(null);
    }

    // transform to LIST DTO
    public List<TaskDTO> convertList(List<Task> tasks){
        return Optional.ofNullable(tasks).map(array -> array.stream().map(
            this::convert
        ).collect(Collectors.toList()))
        
        .orElse(new ArrayList<>());
    }
}
