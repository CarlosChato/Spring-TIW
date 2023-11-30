package Application.repos;

import Application.mysqlControllers.Event;
import Application.mysqlControllers.Ticket;
import Application.mysqlControllers.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketDAO extends CrudRepository<Ticket, Long>{
    List<Ticket> findAll();
    List<Ticket> findByEvent(Event event);
    List<Ticket> findByUsers(User users);
}
