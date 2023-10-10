package passion.springboot.passion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ServiceController {
    @GetMapping("/contact")
    public String contact() {
        return"service/contact";
    }
    @GetMapping("/service")
    public String service() {
        return "service/services";
    }
    @GetMapping("/service-details")
    public String service_details() {
        return "service/service-details";
    }
}
