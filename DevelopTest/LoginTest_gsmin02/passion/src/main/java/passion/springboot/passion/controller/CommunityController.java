package passion.springboot.passion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommunityController {
    @GetMapping("/edit")
    public String edit() {
        return "community/edit";
    }

    @GetMapping("/post")
    public String post() {
        return "community/post";
    }

    @GetMapping("/view")
    public String view() {
        return "community/view";
    }

    @GetMapping("/write")
    public String write() {
        return "community/write";
    }
}
