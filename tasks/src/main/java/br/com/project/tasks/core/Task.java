package br.com.project.tasks.core;

import org.springframework.data.annotation.Id;

import br.com.project.tasks.core.constant.TaskState;
import lombok.Data;

@Data
public class Task {
    @Id
    private String id;

    private String title;
    private String description;
    private Integer priority;
    private TaskState state;

    public Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.state = builder.state;
    }

    public Task() {
    }

    public Task insert() {
        return builderFrom(this).withTaskState(TaskState.INSERT).build();
    }

    public String getId(){
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String title;
        private String description;
        private Integer priority;
        private TaskState state;

        public Builder(Task task) {
            this.id = task.id;
            this.title = task.title;
            this.description = task.description;
            this.priority = task.priority;
            this.state = task.state;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder() {
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPriority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder withTaskState(TaskState state) {
            this.state = state;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    public static Builder builderFrom(Task task) {
        return new Builder(task);
    }
}
