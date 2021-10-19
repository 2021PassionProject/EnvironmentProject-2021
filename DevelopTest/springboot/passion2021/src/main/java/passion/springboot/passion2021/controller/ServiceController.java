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

    @GetMapping("/service-details2")
    public String servicedetails2() {
        return "service/service-details2";
    }

    @GetMapping("/service-details3")
    public String servicedetails3() {
        return "service/service-details3";
    }

    @GetMapping("/service-details4")
    public String servicedetails4() {
        return "service/service-details4";
    }

    @GetMapping("/service-details5")
    public String servicedetails5() {
        return "service/service-details5";
    }

    @GetMapping("/service-details6")
    public String servicedetails6() {
        return "service/service-details6";
    }

    @GetMapping("/services")
    public String services() {
        return "service/services";
    }
}