package passion.springboot.passion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Spring Web MVC 컨트롤러
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @REstController  // REstful 웹 서비스 컨트롤러
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String goHome() {
        return "main/index";
    }
    @GetMapping("index")
    public String Home() {
        return "main/index";
    }
}