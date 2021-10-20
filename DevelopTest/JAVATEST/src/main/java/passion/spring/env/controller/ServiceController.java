package passion.spring.env.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ServiceController {

    @GetMapping("/services")
    public String services() {
        return "service/services";
    }
    @GetMapping("/service-details1")
    public String serviceDetails1() {
        return "service/service-details1";
    }
    @GetMapping("/service-details2")
    public String serviceDetails2() {
        return "service/service-details2";
    }
    @GetMapping("/service-details3")
    public String serviceDetails3() {
        return "service/service-details3";
    }
    @GetMapping("/service-details4")
    public String serviceDetails4() {
        return "service/service-details4";
    }
    @GetMapping("/service-details5")
    public String serviceDetails5() {
        return "service/service-details5";
    }
    @GetMapping("/service-details6")
    public String serviceDetails6() {
        return "service/service-details6";
    }
}
