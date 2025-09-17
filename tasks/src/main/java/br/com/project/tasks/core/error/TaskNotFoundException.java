package br.com.project.tasks.core.error;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(){
        super("Task not found");
    }
}
