package br.com.fiap.epictaskapi.config;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class databaseSeed implements CommandLineRunner{

    @Autowired
    TaskRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(List.of(
                new Task("Modelar o BD", "Modelar as tabelas do banco de dados", 100, 0),
                new Task("Prototipar páginas web", "Prototipar telas para as páginas do front end", 400, 0),
                new Task("Atualizar arquitetura", "atualizar arquitetura de backend dados", 55, 0)
        ))
    }
}
