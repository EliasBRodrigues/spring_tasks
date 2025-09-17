package br.com.project.tasks.core.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskUpdateDTO {
    @NotBlank(message = "{invalid.id}")
    private String id;

    @NotBlank(message = "{invalid.title}")
    @Size(min = 3, max = 20, message = "title must 3-20 caracters")
    private String title;

    @NotBlank(message = "{invalid.description}")
    @Size(min = 10, max = 50, message = "title must 3-20 caracters")
    private String description;

    @Min(value = 1, message = "{invalid.priority}")
    private Integer priority;
}
