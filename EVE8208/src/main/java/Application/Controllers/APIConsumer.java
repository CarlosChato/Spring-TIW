package Application.Controllers;

import Application.mysqlControllers.Event;
import Application.repos.EventDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.Optional;

/* API Controller para pasar a los controladores que haran cosas nazis Peter */

@RestController
public class APIConsumer {

    @Autowired
    private EventDAO eventStore;

    @RequestMapping(value="/eventos", method = RequestMethod.GET)
    public @ResponseBody List<Event> getAllEvents() {
        // Extraer todos los eventos de la DB
        List<Event> events = eventStore.findAll();
        return events;
    }

    @RequestMapping(value="/eventos/{id}", method = RequestMethod.GET)
    public @ResponseBody Event getEvent(@PathVariable String id) {
        // Extraer de la DB tan solo el evento con ese id

        // Initialization event obj at null, if front end recibe a null, it's mean that it couldn't find anything
        Event event = null;
        // Try to do the operation
        try {
            // Parse to Long our id
            Long id_long = Long.parseLong(id);
            // Try to find event, if it doesn't exist it will return null
            event = eventStore.findById(id_long).orElse(null);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return Our object, it could be null or Event obj
        return event;
    }

    @RequestMapping(value="/eventos/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Event deleteEvent(@PathVariable String id) {
        // eliminar el evento id de la base de datos

        // It returns null or object event that we deleted, to inform the client
        Event event = null;
        try{
            // Search the event
            event = eventStore.findById(Long.parseLong(id)).orElse(null);

            // Delete event
            eventStore.delete(event);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return null if it couldn't find the event by id or event if it was success
        return event;

    }

    @RequestMapping(value="/eventos/{id}", method = RequestMethod.PUT)
    public @ResponseBody Event updateEvent(@PathVariable String id, @RequestBody Event newEvent) {
        // Objeto que llega usado para updatear el evento con ese id

        Event eventToUpdate = null;

        try{
            // Search if the event exists
            eventToUpdate = eventStore.findById(Long.parseLong(id)).orElse(null);

            // If it is != null means that it exists
            if(eventToUpdate != null){
                // Update the event with the new info
                eventStore.save(newEvent);
                eventToUpdate = newEvent;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return null or the Event updated
        return eventToUpdate;
    }

    @RequestMapping(value="/eventos", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Event addEvent(@RequestBody Event event) {
        // Insertar un elemento en la DB
        try {
            eventStore.save(event);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return event;
    }

}
