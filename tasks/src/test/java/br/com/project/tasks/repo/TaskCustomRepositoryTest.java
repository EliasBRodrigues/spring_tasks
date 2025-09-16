package br.com.project.tasks.repo;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoOperations;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.data.repository.TaskCustomRepository;
import br.com.project.tasks.utils.TestUtils;
import reactor.core.publisher.Mono;

@SpringBootTest
public class TaskCustomRepositoryTest {
    @InjectMocks
    private TaskCustomRepository taskCustomRepository;

    @Mock
    private MongoOperations mongoOperations;

    @Test
    void customRepository_mustReturnPageWithOneElement_whenSendTask() {
        Task task = TestUtils.buildValidTask();
        Mockito.when(mongoOperations.find(any(), any())).thenReturn(List.of(task));
        Mono<Page<Task>> result = taskCustomRepository.findPaginated(task, 0, 10);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.block().getNumberOfElements());
    }
}
