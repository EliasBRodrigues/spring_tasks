package br.com.project.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.data.repository.TaskRepository;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
    // public static List<Task> tasks = new ArrayList<>();

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Mono: fluxo de dados que tem um item/ou nenhum
    public Mono<Task> insert(Task task) {
        return Mono.just(task)
                .map(Task::insert) // objetos simples
                .flatMap(it -> this.save(it)); // ou this::save -> funcoes/lambda
    }

    public Mono<List<Task>> list() {
        return Mono.just(taskRepository.findAll());
    }

    private Mono<Task> save(Task task) {
        return Mono.just(task).map(taskRepository::save);
    }
}
