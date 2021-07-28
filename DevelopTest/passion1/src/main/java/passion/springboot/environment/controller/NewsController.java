package passion.springboot.environment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NewsController {
    @GetMapping("/blog")
    public String blog(){return "news/blog";}

    @GetMapping("/blog-details")
    public String blogdetails(){return "news/blog-details";}
}
