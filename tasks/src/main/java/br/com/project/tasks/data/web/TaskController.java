package br.com.project.tasks.data.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.tasks.core.constant.TaskState;
import br.com.project.tasks.core.model.TaskDTO;
import br.com.project.tasks.core.model.TaskInsertDTO;
import br.com.project.tasks.core.model.TaskUpdateDTO;
import br.com.project.tasks.core.model.func.TaskDTOConverter;
import br.com.project.tasks.core.model.func.TaskInsertDTOConverter;
import br.com.project.tasks.core.model.func.TaskUpdateDTOConverter;
import br.com.project.tasks.service.TaskService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/task")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);


    private final TaskService taskService;
    private final TaskDTOConverter converter;
    private final TaskInsertDTOConverter taskInsertDTOConverter;
    private final TaskUpdateDTOConverter taskUpdateDTOConverter;

    public TaskController(TaskService taskService, TaskDTOConverter converter, TaskInsertDTOConverter taskInsertDTOConverter, TaskUpdateDTOConverter taskUpdateDTOConverter) {
        this.taskService = taskService;
        this.converter = converter;
        this.taskInsertDTOConverter = taskInsertDTOConverter;
        this.taskUpdateDTOConverter = taskUpdateDTOConverter;
    }

    @GetMapping("/tasks")
    public Mono<Page<TaskDTO>> getTasks(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "0") int priority,
            @RequestParam(required = false) TaskState state,
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return taskService.findPaginated(
                    converter.convert(id, title, description, priority, state), pageNumber, pageSize)
                    .map(it -> it.map(converter::convert));
    }

    @PostMapping("/insert")
    public Mono<TaskDTO> insertTask(@RequestBody TaskInsertDTO taskInsertDTO) {
        return taskService.insert(taskInsertDTOConverter.convert(taskInsertDTO))
                    .doOnNext(task -> LOGGER.info("task id save {}", task.getId()))
                    .map(converter::convert);
    }

    @PutMapping("/update")
    public Mono<TaskDTO> updateTask(@RequestBody TaskUpdateDTO taskUpdateDTO) {
        return taskService.update(taskUpdateDTOConverter.convert(taskUpdateDTO)).doOnNext(it -> LOGGER.info("updated task id", it.getId())).map(converter::convert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable String id){
        return Mono.just(id).doOnNext(task -> LOGGER.info("task id deleted {}", id)).
        flatMap(taskService::deleteById);
    }

}
