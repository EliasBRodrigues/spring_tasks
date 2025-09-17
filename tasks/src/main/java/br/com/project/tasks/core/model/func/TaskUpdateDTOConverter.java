package br.com.project.tasks.core.model.func;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.model.TaskUpdateDTO;

@Component
public class TaskUpdateDTOConverter {
    public Task convert(TaskUpdateDTO taskInsertDTO) {
        return Optional
            .ofNullable(taskInsertDTO)
            .map(source -> Task.builder()
                .withId(source.getId())
                .withTitle(source.getTitle())
                .withDescription(source.getDescription())
                .withPriority(source.getPriority())
                .build())
                .orElse(null);
    }
}
