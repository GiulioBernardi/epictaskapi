package br.com.fiap.epictaskapi.controller;

import br.com.fiap.epictaskapi.dto.UserDTO;
import br.com.fiap.epictaskapi.dto.mapper.MapperUserDTO;
import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.service.AuthenticationService;
import br.com.fiap.epictaskapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserService userService;

    @Autowired
    private MapperUserDTO mapper;

    @GetMapping
    public Page<UserDTO> getAll(@PageableDefault(size = 5) Pageable paginacao){
        List<UserDTO> users = userService.listAll(paginacao).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(users);
    }


    //remover
    @GetMapping("/detalhes/{email}")
    public UserDetails getget(@PathVariable String email){
        return authService.loadUserByUsername(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> show(@PathVariable Long id){
        Optional<UserDTO> userFromDb = userService.findById(id).stream().map(mapper::toDto).findFirst();
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
    @CacheEvict(value="users", allEntries = true)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO updatedUser){
        Optional<User> userFromDb = userService.findById(id);
        if(userFromDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user = userFromDb.get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(userFromDb.get().getPassword());

        userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }
}