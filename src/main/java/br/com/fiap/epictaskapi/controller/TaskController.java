package br.com.fiap.epictaskapi.controller;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/task")

public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    public List<Task> getAll(){
        return taskService.listAll();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid Task task){
        taskService.save(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> show(@PathVariable Long id){
        Optional<Task> task = taskService.findById(id);
        System.out.println(task);
        if(task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Task>  optional = taskService.findById(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        taskService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody @Valid Task newTask){
        Optional<Task> optional = taskService.findById(id);
        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Task task = optional.get();
        BeanUtils.copyProperties(newTask, task);
        taskService.update(task);
        return ResponseEntity.ok(task);
    }

}
