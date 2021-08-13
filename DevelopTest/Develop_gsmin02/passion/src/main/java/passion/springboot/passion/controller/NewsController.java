package passion.springboot.passion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NewsController {
    @GetMapping("/blog")
    public String blog() {
        return "news/blog";
    }
    @GetMapping("/blog-details")
    public  String blog_details() {
        return "news/blog-details";
    }
}
