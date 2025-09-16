package br.com.project.tasks.data.repository;

// import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.project.tasks.core.Task;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String>{
    
}
