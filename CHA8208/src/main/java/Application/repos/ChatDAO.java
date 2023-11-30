package Application.repos;

import Application.mysqlControllers.Chat;
import Application.mysqlControllers.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatDAO extends CrudRepository<Chat, Long> {

    List<Chat> findAll();

    List<Chat> findByUser1AndUser2(User user1, User user2);

    List<Chat> findByUser1(User user);

    List<Chat> findByUser2(User user);

}
