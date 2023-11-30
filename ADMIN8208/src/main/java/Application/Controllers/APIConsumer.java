package Application.Controllers;

import Application.mysqlControllers.Event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class APIConsumer {

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String responseAdminPanel(){
        return "adminPanel";
    }
}
