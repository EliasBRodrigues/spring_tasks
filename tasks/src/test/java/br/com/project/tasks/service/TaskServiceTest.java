package br.com.project.tasks.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.data.repository.TaskCustomRepository;
import br.com.project.tasks.data.repository.TaskRepository;
import br.com.project.tasks.utils.TestUtils;
import reactor.test.StepVerifier;

@SpringBootTest
public class TaskServiceTest {
    
    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;
    
    @Mock
    private TaskCustomRepository taskCustomRepository;

    @Test
    void service_mustReturnTask_whenInsertSuccessfully(){
        Task task = TestUtils.buildValidTask();

        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);

        StepVerifier.create(taskService.insert(task))
                        .then(() -> Mockito.verify(taskRepository, 
                        Mockito.times(1)) // verifiy save method
                        .save(Mockito.any()))
                        .expectNext(task).expectComplete();
    }

    @Test
    void service_mustReturnTask_whenDeleteSuccessfully() {
        StepVerifier.create(taskService.deleteById("any-id"))
                    .then(() -> verify(taskRepository, times(1)).deleteById(any()))
                    .verifyComplete();
    }

    @Test
    void service_mustReturnTask_whenFindPaginated() {
        Task task = TestUtils.buildValidTask();
        Mockito.when(taskCustomRepository.findPaginated(any(), anyInt(), anyInt())).thenReturn(Page.empty());
        Page<Task> result = taskService.findPaginated(task, 0, 10);
        Assertions.assertNotNull(result);
        Mockito.verify(taskCustomRepository, times(1)).findPaginated(any(), anyInt(), anyInt());
    }

}
