package br.com.fiap.epictaskapi.repository;

import br.com.fiap.epictaskapi.model.Usuario;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String username);
}
