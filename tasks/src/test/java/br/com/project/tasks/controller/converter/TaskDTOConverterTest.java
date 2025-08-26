package br.com.project.tasks.controller.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.constant.TaskState;
import br.com.project.tasks.core.model.TaskDTO;
import br.com.project.tasks.core.model.TaskDTOConverter;
import br.com.project.tasks.utils.TestUtils;

@SpringBootTest
public class TaskDTOConverterTest {

    @InjectMocks
    private TaskDTOConverter converter;

    @Test
    void converter_mustReturnTaskDTO_whenInputTask() {
        Task task = TestUtils.buildValidTask();

        TaskDTO taskDTO = converter.convert(task);

        Assertions.assertEquals(taskDTO.getId(), task.getId());
        Assertions.assertEquals(taskDTO.getTitle(), task.getTitle());
        Assertions.assertEquals(taskDTO.getDescription(), task.getDescription());
        Assertions.assertEquals(taskDTO.getPriority(), task.getPriority());
        Assertions.assertEquals(taskDTO.getState(), task.getState());
    }

    @Test
    void converter_mustReturnTask_whenInputTaskDTO() {
        TaskDTO taskDTO = TestUtils.buildValidTaskDTO();

        Task task = converter.convert(taskDTO);
        Assertions.assertEquals(task.getId(), taskDTO.getId());
        Assertions.assertEquals(task.getTitle(), taskDTO.getTitle());
        Assertions.assertEquals(task.getDescription(), taskDTO.getDescription());
        Assertions.assertEquals(task.getPriority(), taskDTO.getPriority());
        Assertions.assertEquals(task.getState(), taskDTO.getState());
        Assertions.assertEquals(task.getId(), taskDTO.getId());
    }

    @Test
    void converter_mustReturnTask_whenInputParameters() {
        String id = "123";
        String title = "title";
        String description = "description";
        Integer priority = 1;
        TaskState state = TaskState.INSERT;

        Task task = converter.convert(id, title, description, priority, TaskState.INSERT);
        Assertions.assertEquals(id, task.getId());
        Assertions.assertEquals(title, task.getTitle());
        Assertions.assertEquals(description, task.getDescription());
        Assertions.assertEquals(priority, task.getPriority());
        Assertions.assertEquals(state, task.getState());
    }
}
