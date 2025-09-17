package br.com.project.tasks.core.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskInsertDTO {
    
    @NotBlank(message = "invalid value in title")
    @Size(min = 3, max = 20, message = "title must 3-20 caracters")
    private String title;
    
    @NotBlank(message = "invalid value in description")
    @Size(min = 10, max = 50, message = "title must 3-20 caracters")
    private String description;

    @Min(value = 1, message = "priority must be max than 0")
    private Integer priority;
}
