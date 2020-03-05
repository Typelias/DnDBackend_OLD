package se.typelias.dndbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import se.typelias.dndbackend.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
