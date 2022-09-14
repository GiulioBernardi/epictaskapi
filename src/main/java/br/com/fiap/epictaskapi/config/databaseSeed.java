package br.com.fiap.epictaskapi.config;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.model.User;

import br.com.fiap.epictaskapi.repository.TaskRepository;
import br.com.fiap.epictaskapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class databaseSeed implements CommandLineRunner{

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User()
                .name("Admin")
                .email("admin@fiap.com.br")
                .password(passwordEncoder.encode("administrador"))
        );
        userRepository.save( new User()
                .name("Giulio")
                .email("giulio@fiap.com")
                .password(passwordEncoder.encode("1234"))
        );
        taskRepository.saveAll(List.of(
                new Task("Modelar o BD", "Modelar as tabelas do banco de dados", 100, 0),
                new Task("Prototipar páginas web", "Prototipar telas para as páginas do front end", 400, 0),
                new Task("Atualizar arquitetura", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Bug", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Deploy", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Análise", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Testes", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Criar token", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Documentar", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Apresentar", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Diagramar", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Negligenciar", "atualizar arquitetura de backend dados", 55, 0),
                new Task("Bolar", "atualizar arquitetura de backend dados", 55, 0)
        ));
    }
}
