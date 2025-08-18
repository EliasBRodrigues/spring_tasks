package br.com.project.tasks.data.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.model.TaskDTO;
import br.com.project.tasks.core.model.TaskDTOConverter;
import br.com.project.tasks.service.TaskService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web")
public class TaskController {

    private final TaskService taskService;
    private final TaskDTOConverter converter;

    public TaskController(TaskService taskService, TaskDTOConverter converter) {
        this.taskService = taskService;
        this.converter = converter;
    }
    
    @GetMapping("/list-task")
    public Mono<List<TaskDTO>> getTasks(){
        //return taskService.list();
        return taskService.list().map(converter::convertList);
    }

    @PostMapping("/insert-task")
    public Mono<TaskDTO> insertTask(@RequestBody Task task) {
       // return taskService.insert(task);
       return taskService.insert(task).map(converter::convert);
    }

}
