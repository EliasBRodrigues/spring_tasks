package br.com.project.tasks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.project.tasks.core.Address;
import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.error.TaskNotFoundException;
import br.com.project.tasks.data.repository.TaskCustomRepository;
import br.com.project.tasks.data.repository.TaskRepository;
import br.com.project.tasks.data.web.TaskController;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskRepository taskRepository;
    private final TaskCustomRepository taskCustomRepository;
    private final AddressService addressService;

    public TaskService(TaskRepository taskRepository, TaskCustomRepository taskCustomRepository, AddressService addressService) {
        this.taskRepository = taskRepository;
        this.taskCustomRepository = taskCustomRepository;
        this.addressService = addressService;
    }

    public Mono<Page<Task>> findPaginated(Task task, Integer pageNumber, Integer pageSize) {
        return taskCustomRepository.findPaginated(task, pageNumber, pageSize);
    }

    // Mono: fluxo de dados que tem um item/ou nenhum
    public Mono<Task> insert(Task task) {
        return Mono.just(task)
                .map(Task::insert) // objetos simples
                .flatMap(it -> this.save(it))// ou this::save -> funcoes/lambda
                .doOnError(error -> LOGGER.info("error during save task, id: ", task.getTitle(), error));
    }

    // metodo de start na tarefa
    public Mono<Task> startTask(String id, String zipCode){
        return taskRepository.findById(id)
            .zipWhen(it -> addressService.getAddress(zipCode))
                .flatMap(it -> updateAddres(it.getT1(), it.getT2()))
                .map(Task::startTask)
                .flatMap(taskRepository::save)
                .switchIfEmpty(Mono.error(TaskNotFoundException::new))
                .doOnError(error -> LOGGER.error("error on start task, id {}", id, error));
    }

    // metodo para atualizar endereco da tarefa
    private Mono<Task> updateAddres(Task task, Address address){
        return Mono.just(task).map(it -> task.updateAddress(address));
    }

    private Mono<Task> save(Task task) {
        return Mono.just(task).doOnNext(t -> LOGGER.info("saving task with title", task.getTitle()))
                .flatMap(taskRepository::save); // enviar atributo reativo
    }

    public Mono<Task> update(Task task) {
        return taskRepository.findById(task.getId())
            .map(task::update)
            .flatMap(taskRepository::save)
            .switchIfEmpty(Mono.error(TaskNotFoundException::new))
            .doOnError(e -> LOGGER.error("Error during update task id {}", task.getId(), e.getMessage()));
    }

    public Mono<Void> deleteById(String id) {
       return taskRepository.deleteById(id);
    }
}
