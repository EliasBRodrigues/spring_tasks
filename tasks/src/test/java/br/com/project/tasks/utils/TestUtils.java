package br.com.project.tasks.utils;

import br.com.project.tasks.core.Task;
import br.com.project.tasks.core.constant.TaskState;
import br.com.project.tasks.core.model.TaskDTO;

public class TestUtils {
    public static Task buildValidTask() {
        return Task.builder()
                        .withId("123")
                        .withTitle("title")
                        .withDescription("Descritpion")
                        .withPriority(1)
                        .withTaskState(TaskState.INSERT)
                        .build();
    }

    public static TaskDTO buildValidTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId("123");
        taskDTO.setDescription("Description");
        taskDTO.setTitle("title");
        taskDTO.setPriority(1);
        taskDTO.setState(TaskState.INSERT);
        return taskDTO;
    }
}
