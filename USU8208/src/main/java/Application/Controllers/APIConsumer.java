package Application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Application.mysqlControllers.User;
import Application.repos.UserDAO;

import java.util.List;

@RestController
public class APIConsumer {

    @Autowired
    private UserDAO userStore;
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers(){
        // Method to get all users
        List<User> users = userStore.findAll();
        return users;
    }

    @RequestMapping(value = "/users/login/{username}", method = RequestMethod.GET)
    public @ResponseBody User getUserLogin(@PathVariable String username){
        User user = null;
        try {
            user = userStore.findByUsername(username);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return user;
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable String id){
        // Extraer de la DB tan solo el usuario con ese id

        // Initialization user obj at null, if front end recibe a null, it's mean that it couldn't find anything
        User user = null;
        // Try to do the operation
        try {
            // Parse to Long our id
            Long id_long = Long.parseLong(id);
            // Try to find user, if it doesn't exist it will return null
            user = userStore.findById(id_long).orElse(null);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return Our object, it could be null or User obj
        return user;
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public @ResponseBody User deleteUser(@PathVariable String id){
        // eliminar el evento id de la base de datos

        // It returns null or object user that we deleted, to inform the client
        User user = null;
        try{
            // Search the user
            user = userStore.findById(Long.parseLong(id)).orElse(null);

            // Delete user
            userStore.delete(user);
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return null if it couldn't find the user by id or user if it was success
        return user;
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.PUT)
    public @ResponseBody User updateUser(@PathVariable String id, @RequestBody User newUser){
        // Objeto que llega usado para updatear el evento con ese id

        User userToUpdate = null;

        try{
            // Search if the user exists
            userToUpdate = userStore.findById(Long.parseLong(id)).orElse(null);

            // If it is != null means that it exists
            if(userToUpdate != null){
                // Update the user with the new info
                userStore.save(newUser);
                userToUpdate = newUser;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Return null or the User updated
        return userToUpdate;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public @ResponseBody User addUser(@RequestBody User user ){
        // Insertar un elemento en la DB
        try {
            userStore.save(user);
            return user;
        }
        catch (Exception e){
            System.out.println(e);
            return new User((long) -1, false, "a", "a","a","a","a","1");
        }
    }
}
