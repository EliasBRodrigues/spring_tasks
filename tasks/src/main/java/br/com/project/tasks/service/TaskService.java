package br.com.project.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.tasks.core.Task;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
    public static List<Task> tasks;

    // Mono: fluxo de dados que tem um item/ou nenhum
    public Mono<Task> monoTasks(Task task) {
        return Mono.just(task) // objetos simples
        .flatMap(it -> this.save(it)); // ou this::save -> funcoes/lambda
    }

    private Mono<Task> save(Task task){
        return Mono.just(task).map(Task :: newTask);
    }
}
