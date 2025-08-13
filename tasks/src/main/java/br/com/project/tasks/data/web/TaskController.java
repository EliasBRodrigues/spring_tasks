package br.com.project.tasks.data.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.service.TaskService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list-task")
    public Mono<List<Task>> getTasks(){
        return taskService.list();
    }

    @PostMapping("/insert-task")
    public Mono<Task> insertTask(@RequestBody Task task) {
        return taskService.insert(task);
    }

}
