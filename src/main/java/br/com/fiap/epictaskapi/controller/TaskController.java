package br.com.fiap.epictaskapi.controller;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/task")

public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    @Cacheable("tasks")
    public Page<Task> getAll(@PageableDefault(size = 5) Pageable paginacao){
        return taskService.listAll(paginacao);
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
    @PreAuthorize("authenticated()")
    @CacheEvict(value = "tasks", allEntries = true)
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
