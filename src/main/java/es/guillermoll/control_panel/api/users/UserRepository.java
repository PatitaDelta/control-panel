package es.guillermoll.control_panel.api.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.guillermoll.control_panel.api.users.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
