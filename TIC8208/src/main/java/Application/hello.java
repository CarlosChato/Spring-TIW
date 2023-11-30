package Application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Controller
public class hello {
    ArrayList<String> pepeList = new ArrayList<>();
    boolean switcher = true;
    @PostConstruct
    public void init () {
        pepeList.add("pepe");
        pepeList.add("jose");
        pepeList.add("juan");
    }
    @RequestMapping("/hola")
    public @ResponseBody String responsePeticion(){
        return "hola";
    }

    @RequestMapping(value = "/pepe", method = RequestMethod.GET)
    public @ResponseBody ArrayList<String> pepeGet(@RequestParam(defaultValue="-1", required=false) Integer id) {
        if(id!=-1) {
            switcher = !switcher;
        }
        return pepeList;
    }

    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    public @ResponseBody boolean switchGet() {
        return switcher;
    }

    @RequestMapping(value = "/setpepe", method = RequestMethod.GET)
    public @ResponseBody ArrayList<String> pepeSet(@RequestParam String nombre) {
        pepeList.add(nombre);
        return pepeList;
    }
}
