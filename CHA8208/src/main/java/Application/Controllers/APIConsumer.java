package Application.Controllers;

import Application.mysqlControllers.Chat;
import Application.mysqlControllers.Message;
import Application.mysqlControllers.User;
import Application.repos.ChatDAO;
import Application.repos.MessageDAO;
import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class APIConsumer {

    @Autowired
    ChatDAO chatDAO;

    @Autowired
    MessageDAO messageDAO;

    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public @ResponseBody List<Chat> getAllChatsUser(@RequestBody User user){
        // We use id, referred to the id of the user, we want to get all the chats that they have.
        // First of all we create a null List of chats
        List<Chat> chats = null;

        try {
            // Try to find Chats which user is the user1, meanly, he is the emitter
            // And the second find it's when he is the receiver
            List<Chat> chats1 = chatDAO.findByUser1(user);
            List<Chat> chats2 = chatDAO.findByUser2(user);

            // Concat both list in one, which we return
            chats = Stream.concat(chats1.stream(), chats2.stream()).collect(Collectors.toList());
        }
        catch (Exception e){
            System.out.println(e);
        }
        // If it does not find any chats, we return
        return chats;
    }

    @RequestMapping(value = "/chats/view/{id}",  method = RequestMethod.GET)
    public @ResponseBody List<Message> getConversation(@PathVariable Long id){
        List<Message> messages = null;

        try {
            Optional<Chat> chat = chatDAO.findById(id);
            if (chat.isPresent()) {
                // Get the conversation between user1 and user2
                messages = messageDAO.findByChat(chat.get());
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return messages;

    }

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public @ResponseBody List<Chat> getAllChats(){
        // Used to debug, returning all chats in DB.
        List<Chat> chats = null;

        try {
            chats = chatDAO.findAll();
        }
        catch (Exception e){
            System.out.println(e);
        }

        // If it does not find any chats, we return
        return chats;
    }

    @RequestMapping(value = "/chats/newMessage", method = RequestMethod.POST)
    public @ResponseBody Message postChat(@RequestBody Message msg){

        Message res = null;
        try {
            res = messageDAO.save(msg);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return  res;
    }

    @RequestMapping(value = "/chats/newChat", method = RequestMethod.POST)
    public @ResponseBody Chat createChat(@RequestBody Chat newChat){
        Chat chat = null;

        try {
            chat = chatDAO.save(newChat);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return chat;
    }

}
