package br.com.project.tasks.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.data.repository.TaskCustomRepository;
import br.com.project.tasks.data.repository.TaskRepository;
import reactor.core.publisher.Mono;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskCustomRepository taskCustomRepository;

    public TaskService(TaskRepository taskRepository, TaskCustomRepository taskCustomRepository) {
        this.taskRepository = taskRepository;
        this.taskCustomRepository = taskCustomRepository;
    }

    // Mono: fluxo de dados que tem um item/ou nenhum
    public Mono<Task> insert(Task task) {
        return Mono.just(task)
                .map(Task::insert) // objetos simples
                .flatMap(it -> this.save(it)); // ou this::save -> funcoes/lambda
    }

    public Page<Task> findPaginated(Task task, Integer pageNumber, Integer pageSize) {
        return taskCustomRepository.findPaginated(task, pageNumber, pageSize);
    }

    private Mono<Task> save(Task task) {
        return Mono.just(task).map(taskRepository::save);
    }

    // route returns void
    public Mono<Void> deleteById(String id){
        return Mono.fromRunnable(() -> taskRepository.deleteById(id));
    }
}
