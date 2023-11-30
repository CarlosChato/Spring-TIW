package Application.repos;

import Application.mysqlControllers.Chat;
import Application.mysqlControllers.Message;
import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageDAO extends CrudRepository<Message,Long> {

    List<Message> findAll();

    List<Message> findByChat(Chat chat);

}
