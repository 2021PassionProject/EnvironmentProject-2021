package passion.spring.env.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UpcyclingController {
    @GetMapping("/upcycling")
    public String upCycling() {
        return "upcycling/upcycling";
    }
}