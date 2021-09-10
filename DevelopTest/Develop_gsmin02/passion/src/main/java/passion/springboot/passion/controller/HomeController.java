package passion.springboot.passion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller // Spring Web MVC 컨트롤러
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @REstController  // Restful 웹 서비스 컨트롤러
public class HomeController {

    @GetMapping("") // url : http://<server_ip>:<port>/
    public String goHome() {

        return "main/index";    // index.html 파일을 view or template으로 사용함
    }
}
