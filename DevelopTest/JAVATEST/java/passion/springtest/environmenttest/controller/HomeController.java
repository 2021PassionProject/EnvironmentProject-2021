package passion.spring.environment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import passion.spring.environment.service.MemberServiceUsingDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller // Spring Web MVC 컨트롤러
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @REstController  // Restful 웹 서비스 컨트롤러
@RequestMapping("/")
public class HomeController {
    MemberServiceUsingDAO memberService;
    @Autowired  // Spring Framework가 주입함.
    public HomeController(MemberServiceUsingDAO memberService) {
        this.memberService = memberService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }

    @GetMapping("") // url : http://<server_ip>:<port>/
    public String goHome(HttpServletRequest request, HttpServletResponse response) {
        return "main/index";    // index.html 파일을 view or template으로 사용함
    }
}
