package Application.Controllers;


import Application.mysqlControllers.Event;
import Application.mysqlControllers.User;
import Application.mysqlControllers.Ticket;
import Application.repos.TicketDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIConsumer {

    @Autowired
    private TicketDAO ticketStore;
    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getAllTickets(){
        // Extraer todos los eventos de la DB
        List<Ticket> events = ticketStore.findAll();
        return events;
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
    public @ResponseBody Ticket getTicket(@PathVariable String id){
        // Extraer de la DB tan solo el ticket con ese id

        // Initialization ticket obj at null, if front end recibe a null, it's mean that it couldn't find anything
        Ticket ticket = null;
        // Try to do the operation
        try {
            // Parse to Long our id
            Long id_long = Long.parseLong(id);
            // Try to find ticket, if it doesn't exist it will return null
            ticket = ticketStore.findById(id_long).orElse(null);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return Our object, it could be null or Ticket obj
        return ticket;
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Ticket deleteTicket(@PathVariable String id){
        // eliminar el ticket id de la base de datos
        // It returns null or object ticket that we deleted, to inform the client
        Ticket ticket = null;
        try{
            // Search the ticket
            ticket = ticketStore.findById(Long.parseLong(id)).orElse(null);

            // Delete ticket
            ticketStore.delete(ticket);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return null if it couldn't find the ticket by id or ticket if it was success
        return ticket;
    }

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.PUT)
    public @ResponseBody Ticket updateTicket(@PathVariable String id, @RequestBody Ticket newTicket){
        // Objeto que llega usado para updatear el evento con ese id

        Ticket ticketToUpdate = null;

        try{
            // Search if the ticket exists
            ticketToUpdate = ticketStore.findById(Long.parseLong(id)).orElse(null);

            // If it is != null means that it exists
            if(ticketToUpdate != null){
                // Update the ticket with the new info
                ticketStore.save(newTicket);
                ticketToUpdate = newTicket;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return null or the Ticket updated
        return ticketToUpdate;
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public @ResponseBody Ticket createTicket(@RequestBody Ticket ticket){
        // Insertar un elemento en la DB
        try {
            ticketStore.save(ticket);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ticket;
    }

    @RequestMapping(value = "/eventos/tickets", method = RequestMethod.POST)
    public @ResponseBody List<Ticket> getTicketByEvent(@RequestBody Event event){
        // Extraer de la DB tan solo el evento con ese id

        // Initialization ticket obj at null, if front end recibe a null, it's mean that it couldn't find anything
        List<Ticket> tickets = null;
        // Try to do the operation
        try {

            // Try to find tickets, if it doesn't exist it will return null
            tickets = ticketStore.findByEvent(event);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return Our object, it could be null or Ticket obj
        return tickets;
    }

    @RequestMapping(value = "/users/tickets", method = RequestMethod.POST)
    public @ResponseBody List<Ticket> getTicketByUser(@RequestBody User user){
        // Extraer de la DB tan solo el user con ese id

        // Initialization user obj at null, if front end recibe a null, it's mean that it couldn't find anything
        List<Ticket> tickets = null;
        // Try to do the operation
        try {

            // Try to find users, if it doesn't exist it will return null
            tickets = ticketStore.findByUsers(user);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return Our object, it could be null or User obj
        return tickets;
    }
}
