package Application.Controllers;

import Application.mysqlControllers.Event;
import Application.mysqlControllers.Ticket;
import Application.mysqlControllers.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class UserController {

    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    public String responseShowUsers(Model model) throws MalformedURLException {
        StringBuilder content = new StringBuilder();
        URL url = new URL("http://127.0.0.1:10802/users");
                String line;
        Gson gson = new Gson();

        try {
            URLConnection urlConn = url.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            while ((line = buffer.readLine()) != null) {
                content.append(line);
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("IOException handled: " + e.getMessage());
            content = new StringBuilder();
            content.append("[{},{},{}]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        User[] us = gson.fromJson(content.toString(), User[].class);
        model.addAttribute("userList", us);
        return "showUsers";
    }
    
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String responseCreateUser(){
        return "createUser";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String responseCreateUserPOST(@RequestParam("username") String username, @RequestParam("password_user") String password_user, @RequestParam("name_user") String name_user, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("phone") String phone) throws IOException {

        // Creamos el User
        User us = new User(false, name_user, password_user, username, surname, email, phone);

        URL url = new URL("http://127.0.0.1:10802/users/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outStream = connection.getOutputStream();
        Gson gson = new Gson();
        String line;
        outStream.write( gson.toJson(us, User.class)
                .getBytes());

        outStream.flush();
        outStream.close();

        BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        while((line = bufferReader.readLine()) != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(line);
        }

        bufferReader.close();
        connection.disconnect();

        return "redirect:/showUsers";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public String responseUpdateUserPUT(@PathVariable String id, @RequestParam("username") String username, @RequestParam("password_user") String password_user, @RequestParam("name_user") String name_user, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("phone") String phone) throws IOException {

        // Creamos el User
        User us = new User(Long.parseLong(id),false, name_user, password_user, username, surname, email, phone);

        URL url = new URL("http://127.0.0.1:10802/users/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outStream = connection.getOutputStream();
        Gson gson = new Gson();
        String line;
        outStream.write( gson.toJson(us, User.class)
                .getBytes());

        outStream.flush();
        outStream.close();

        BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        while((line = bufferReader.readLine()) != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(line);
        }

        bufferReader.close();
        connection.disconnect();

        return "redirect:/showUsers";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public String responseUpdateUser(Model model, @PathVariable String id){
        model.addAttribute("userId", id);
        return "updateUser";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String responseDeleteUser(@PathVariable String id) throws IOException {
        URL url = new URL("http://127.0.0.1:10802/users/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        System.out.print(responseCode);
        return "redirect:/showUsers";
    }
}