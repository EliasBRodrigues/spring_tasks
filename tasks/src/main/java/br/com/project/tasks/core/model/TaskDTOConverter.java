package br.com.project.tasks.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.constant.TaskState;

@Component
public class TaskDTOConverter {

    // transform Model -> DTO
    public TaskDTO convert(Task task) {
        return Optional.ofNullable(task)
                .map(source -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(source.getId());
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
                        .withId(source.getId())
                        .withTitle(source.getTitle())
                        .withDescription(source.getDescription())
                        .withPriority(source.getPriority())
                        .withTaskState(source.getState())
                        .build())
                .orElse(null);
    }

    // transform to LIST DTO
    // public List<TaskDTO> convertList(List<Task> tasks) {
    //     return Optional.ofNullable(tasks).map(array -> array.stream().map(
    //             this::convert).collect(Collectors.toList()))
    //             .orElse(new ArrayList<>());
    // }

    public Task convert(String id, String title, String description, int priority, TaskState state) {
        return Task.builder().withId(id).withTitle(title).withDescription(description).withPriority(priority)
                .withTaskState(state).build();
    }
}
