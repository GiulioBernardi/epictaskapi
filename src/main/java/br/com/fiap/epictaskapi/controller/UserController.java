package br.com.fiap.epictaskapi.controller;

import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Cacheable("users")
    public Page<User> getAll(@PageableDefault(size = 3) Pageable paginacao){
        return userService.listAll(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> show(@PathVariable Long id){
        Optional<User> userFromDb = userService.findById(id);
        if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userFromDb);
    }


    @DeleteMapping("/{id}")
    @CacheEvict(value="users", allEntries = true)
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<User> userFromDb = userService.findById(id);
        if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User updatedUser){
        Optional<User> userFromDb = userService.findById(id);
        if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user = userFromDb.get();
        BeanUtils.copyProperties(updatedUser, user);
        userService.update(user);
        return ResponseEntity.ok(user);
    }
}