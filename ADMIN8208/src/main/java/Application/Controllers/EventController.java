package Application.Controllers;

import Application.mysqlControllers.Event;
import Application.mysqlControllers.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.net.*;
import java.util.Base64;

import com.google.gson.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EventController {

    @RequestMapping(value = "/showEvents", method = RequestMethod.GET)
    public String responseShowEvents(Model model) throws MalformedURLException {
        StringBuilder content = new StringBuilder();
        URL url = new URL("http://127.0.0.1:10801/eventos");
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
        Event[] ev = gson.fromJson(content.toString(), Event[].class);
        model.addAttribute("eventList", ev);
        return "showEvents";
    }

    @RequestMapping(value = "/createEvent", method = RequestMethod.GET)
    public String responseCreateEvent(){
        return "createEvent";
    }

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String responseCreateEventPOST(@RequestParam("name") String name, @RequestParam("city") String city, @RequestParam("sala") String sala, @RequestParam("fecha") String fecha, @RequestParam("category") String category, @RequestParam("photo") MultipartFile photo) throws Exception {


        InputStream oldfileContent = photo.getInputStream();
        byte[] oldbyteArray = getByteArray(oldfileContent);

        String base64String = Base64.getEncoder().encodeToString(oldbyteArray);

        // Creamos el User
        Event ev = new Event(name, category, fecha, city, sala, base64String);

        URL url = new URL("http://127.0.0.1:10801/eventos/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outStream = connection.getOutputStream();
        Gson gson = new Gson();
        String line;
        outStream.write( gson.toJson(ev, Event.class)
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

        return "redirect:/showEvents";
    }

    @RequestMapping(value = "/updateEvent/{id}", method = RequestMethod.POST)
    public String responseUpdateEventPUT(@PathVariable String id, @RequestParam("name") String name, @RequestParam("city") String city, @RequestParam("sala") String sala, @RequestParam("fecha") String fecha, @RequestParam("category") String category, @RequestParam("photo") MultipartFile photo) throws Exception {


        InputStream oldfileContent = photo.getInputStream();
        byte[] oldbyteArray = getByteArray(oldfileContent);

        String base64String = Base64.getEncoder().encodeToString(oldbyteArray);

        // Creamos el User
        Event ev = new Event(Long.parseLong(id), name, category, fecha, city, sala, base64String);

        URL url = new URL("http://127.0.0.1:10801/eventos/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outStream = connection.getOutputStream();
        Gson gson = new Gson();
        String line;
        outStream.write( gson.toJson(ev, Event.class)
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

        return "redirect:/showEvents";
    }

    @RequestMapping(value = "/updateEvent/{id}", method = RequestMethod.GET)
    public String responseUpdateEvent(Model model, @PathVariable String id){
        model.addAttribute("eventId", id);
        return "updateEvent";
    }

    @RequestMapping(value = "/deleteEvent/{id}", method = RequestMethod.GET)
    public String responseDeleteEvent(@PathVariable String id) throws IOException {
        URL url = new URL("http://127.0.0.1:10801/eventos/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        System.out.print(responseCode);
        return "redirect:/showEvents";
    }

    private static byte[] getByteArray(InputStream is) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        BufferedOutputStream os = new BufferedOutputStream(b);
        int i;
        while (true) {
            i = is.read();
            if (i == -1) break;
            os.write(i);
        }
        os.flush();
        os.close();
        return b.toByteArray();
    }
}

