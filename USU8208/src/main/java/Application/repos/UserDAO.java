package Application.repos;

import Application.mysqlControllers.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByUsername(String username);
}
