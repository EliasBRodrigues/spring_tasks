package br.com.project.tasks.data.web;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.tasks.core.constant.TaskState;
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
    public Page<TaskDTO> getTasks(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "0") int priority,
            @RequestParam(required = false) TaskState state,
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return taskService.findPaginated(converter.convert(id, title, description, priority, state), pageNumber, pageSize)
                .map(converter::convert);
    }

    @PostMapping("/insert-task")
    public Mono<TaskDTO> insertTask(@RequestBody TaskDTO taskDTO) {
        return taskService.insert(converter.convert(taskDTO)).map(converter::convert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable String id){
        return Mono.just(id).flatMap(taskService::deleteById);
    }

}
