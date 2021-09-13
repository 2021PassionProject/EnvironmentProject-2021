package passion.springboot.passion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ServiceController {

    @GetMapping("/contact")
    public String contact() {
        return "service/contact";
    }
    @GetMapping("/services")
    public String services() {
        return "service/services";
    }
    @GetMapping("/service-details")
    public String serviceDetails() {
        return "service/service-details";
    }
}
