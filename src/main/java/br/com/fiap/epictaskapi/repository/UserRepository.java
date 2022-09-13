package br.com.fiap.epictaskapi.repository;

import br.com.fiap.epictaskapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    @Query(value = "SELECT u.id, u.name, u.email from User u")
    Page<User> findAllExcludingPassword(Pageable pageable);
}
