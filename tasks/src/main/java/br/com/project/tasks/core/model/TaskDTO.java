package br.com.project.tasks.core.model;

import br.com.project.tasks.core.constant.TaskState;

public class TaskDTO {
    String id;
    String title;
    String description;
    Integer priority;
    TaskState state;

     public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public TaskState getState() {
        return this.state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}