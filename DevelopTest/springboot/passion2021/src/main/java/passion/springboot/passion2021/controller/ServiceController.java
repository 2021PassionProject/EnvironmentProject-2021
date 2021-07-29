package passion.springboot.passion2021.controller;

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
    @GetMapping("/service-details")
    public String servicedetails() {
        return "service/service-details";
    }
    @GetMapping("/services")
    public String services() {
        return "service/services";
    }
}