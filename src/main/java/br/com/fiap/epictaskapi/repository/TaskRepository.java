package br.com.fiap.epictaskapi.repository;

import br.com.fiap.epictaskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
