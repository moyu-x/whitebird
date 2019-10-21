package top.idwangmo.authservice.entity.repository;

import org.springframework.data.repository.CrudRepository;
import top.idwangmo.authservice.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}

