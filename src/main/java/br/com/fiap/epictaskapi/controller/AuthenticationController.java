package br.com.fiap.epictaskapi.controller;

import br.com.fiap.epictaskapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping
    public ResponseEntity<Object> auth(@RequestBody User user){



        return ResponseEntity.ok(user);
    }
}