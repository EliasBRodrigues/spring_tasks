package br.com.project.tasks.core.model.func;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.model.TaskInsertDTO;

@Component
public class TaskInsertDTOConverter {
    public Task convert(TaskInsertDTO taskInsertDTO) {
        return Optional
            .ofNullable(taskInsertDTO)
            .map(source -> Task.builder()
                .withTitle(source.getTitle())
                .withDescription(source.getDescription())
                .withPriority(source.getPriority())
                .build())
                .orElse(null);
    }
}
