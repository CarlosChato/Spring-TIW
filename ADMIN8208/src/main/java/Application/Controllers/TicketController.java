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

import java.awt.datatransfer.DataFlavor;
import java.io.*;
import java.net.*;

@Controller
public class TicketController {

    @RequestMapping(value = "/showTickets", method = RequestMethod.GET)
    public String responseShowTickets(Model model) throws MalformedURLException {
        StringBuilder content = new StringBuilder();
        URL url = new URL("http://127.0.0.1:10803/tickets");
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
        Ticket[] tk = gson.fromJson(content.toString(), Ticket[].class);
        model.addAttribute("ticketList", tk);
        return "showTickets";
    }

    @RequestMapping(value = "/createTicket", method = RequestMethod.GET)
    public String responseCreateTicket(){
        return "createTicket";
    }

    @RequestMapping(value = "/createTicket", method = RequestMethod.POST)
    public String responseCreateTicketPOST(@RequestParam("price") String price, @RequestParam("type") String type, @RequestParam("code") String code, @RequestParam("id") String id) throws IOException {
        // Evento del Ticket
        StringBuilder content = new StringBuilder();
        URL url = new URL("http://127.0.0.1:10801/eventos/"+id);
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
        Event ev = gson.fromJson(content.toString(), Event.class);

        // Usuario del Ticket
        StringBuilder content2 = new StringBuilder();
        URL url2 = new URL("http://127.0.0.1:10802/users/"+"1");
        String line2;
        Gson gson2 = new Gson();

        try {
            URLConnection urlConn = url2.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            while ((line2 = buffer.readLine()) != null) {
                content2.append(line2);
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("IOException handled: " + e.getMessage());
            content2 = new StringBuilder();
            content2.append("[{},{},{}]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        User us = gson2.fromJson(content2.toString(), User.class);

        // Creamos el Ticket
        Ticket tk = new Ticket(type, code, price, ev, us);

        URL url3 = new URL("http://127.0.0.1:10803/tickets/");
        HttpURLConnection connection3 = (HttpURLConnection) url3.openConnection();
        connection3.setRequestMethod("POST");

        connection3.setRequestProperty("Content-Type", "application/json");
        connection3.setDoOutput(true);
        OutputStream outStream = connection3.getOutputStream();
        outStream.write( gson.toJson(tk, Ticket.class)
                .getBytes());

        outStream.flush();
        outStream.close();

        BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(connection3.getInputStream())
        );

        while((line = bufferReader.readLine()) != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(line);
        }

        bufferReader.close();
        connection3.disconnect();

        return "redirect:/showTickets";
    }

    @RequestMapping(value = "/updateTicket/{id}", method = RequestMethod.POST)
    public String responseUpdateTicketPUT(@PathVariable String id, @RequestParam("price") String price, @RequestParam("type") String type, @RequestParam("code") String code, @RequestParam("event") String event, @RequestParam("user") String user) throws IOException {
        // Evento del Ticket
        StringBuilder content = new StringBuilder();
        URL url = new URL("http://127.0.0.1:10801/eventos/"+event);
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
        Event ev = gson.fromJson(content.toString(), Event.class);

        // Usuario del Ticket
        StringBuilder content2 = new StringBuilder();
        URL url2 = new URL("http://127.0.0.1:10802/users/"+user);
        String line2;
        Gson gson2 = new Gson();

        try {
            URLConnection urlConn = url2.openConnection();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            while ((line2 = buffer.readLine()) != null) {
                content2.append(line2);
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("IOException handled: " + e.getMessage());
            content2 = new StringBuilder();
            content2.append("[{},{},{}]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        User us = gson2.fromJson(content2.toString(), User.class);

        // Creamos el Ticket
        Ticket tk = new Ticket(Long.parseLong(id), type, code, price, ev, us);

        URL url3 = new URL("http://127.0.0.1:10803/tickets/"+id);
        HttpURLConnection connection3 = (HttpURLConnection) url3.openConnection();
        connection3.setRequestMethod("PUT");

        connection3.setRequestProperty("Content-Type", "application/json");
        connection3.setDoOutput(true);
        OutputStream outStream = connection3.getOutputStream();
        outStream.write( gson.toJson(tk, Ticket.class)
                .getBytes());

        outStream.flush();
        outStream.close();

        BufferedReader bufferReader = new BufferedReader(
                new InputStreamReader(connection3.getInputStream())
        );

        while((line = bufferReader.readLine()) != null) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(line);
        }

        bufferReader.close();
        connection3.disconnect();

        return "redirect:/showTickets";
    }

    @RequestMapping(value = "/updateTicket/{id}", method = RequestMethod.GET)
    public String responseUpdateTicket(Model model, @PathVariable String id){
        model.addAttribute("ticketId", id);
        return "updateTicket";
    }

    @RequestMapping(value = "/deleteTicket/{id}", method = RequestMethod.GET)
    public String responseDeleteUser(@PathVariable String id) throws IOException {
        URL url = new URL("http://127.0.0.1:10803/tickets/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        System.out.print(responseCode);
        return "redirect:/showTickets";
    }
}