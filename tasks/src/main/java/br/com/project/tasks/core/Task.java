package br.com.project.tasks.core;

import br.com.project.tasks.core.constant.TaskState;
import br.com.project.tasks.service.TaskService;
import lombok.Data;

@Data
public class Task {
    private String title;
    private String description;
    private Integer priority;
    private TaskState state;

    // adicionando tarefa na lista em service
    public Task newTask(){
        TaskService.tasks.add(this);
        return this;
    }
}
