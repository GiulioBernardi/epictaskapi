package br.com.fiap.epictaskapi.service;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;

    public List<Task> listAll(){
        return repository.findAll();
    }


    public Task save(Task task) {
        return repository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void update(Task task){
        repository.save(task);
    }
}
