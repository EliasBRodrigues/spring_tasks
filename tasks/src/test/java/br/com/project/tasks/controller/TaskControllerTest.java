package br.com.project.tasks.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.model.TaskDTO;
import br.com.project.tasks.core.model.TaskDTOConverter;
import br.com.project.tasks.data.web.TaskController;
import br.com.project.tasks.service.TaskService;
import reactor.core.publisher.Mono;

@SpringBootTest
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskDTOConverter taskDTOConverter;

    @Test
    public void controller_mustReturnOk_whenSaveSuccessfully() {
        Mockito.when(taskDTOConverter.convert(Mockito.any(Task.class))).thenReturn(new TaskDTO()); // return new
                                                                                                   // Task-Mono

        Mockito.when(taskService.insert(Mockito.any())).thenReturn(Mono.just(new Task())); // return Task-DTO

        WebTestClient webTestClient = WebTestClient.bindToController(taskController).build();

        webTestClient.post()
                .uri("/web/insert-task")
                .bodyValue(new TaskDTO())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TaskDTO.class);
    }

    @Test
    public void controller_mustReturnOk_whenGetSuccessfully() {

        Mockito.when(taskService.findPaginated(any(), anyInt(), anyInt())).thenReturn(Page.empty());

        WebTestClient webTestClient = WebTestClient.bindToController(taskController).build();

        webTestClient.get().uri("/web/list-task")
            .exchange()
            .expectStatus().isAccepted()
            .expectBody(TaskDTO.class);
    }

    @Test
    public void controller_mustReturnOk_whenDeleteSuccessfully() {
        String id = "any-id";

        Mockito.when(taskService.deleteById(any())).thenReturn(Mono.empty());

        WebTestClient webTestClient = WebTestClient.bindToController(taskController).build();

        webTestClient.delete()
            .uri("/web/" + id)
            .exchange()
            .expectStatus()
            .isNoContent();
    }
}
