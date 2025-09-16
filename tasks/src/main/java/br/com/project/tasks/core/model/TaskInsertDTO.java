package br.com.project.tasks.core.model;

import lombok.Data;

@Data
public class TaskInsertDTO {
    private String title;
    private String description;
    private Integer priority;
}
