package passion.springboot.environment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import passion.springboot.environment.Service.MemberService;
import passion.springboot.environment.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MemberController {
    HttpSession session = null;
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "member/mypage";
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signin")
    public String signin(HttpServletRequest request, Model model) {
        session = request.getSession();
        String email = request.getParameter("email"); // email-> id
        String pw = request.getParameter("pw");
        Member retMember = memberService.getMemberByEmail(email); 
        if (retMember != null && retMember.getPw().equals(pw)) { //로그인 성공
            session.setAttribute("name",retMember.getName());
            session.setMaxInactiveInterval(-1);
            /*session.setAttribute("id",retMember.getId());*/
            return "main/index";
        } else {
              model.addAttribute("message","로그인 실패");
            /*return "errors/message";*/
            return "member/signup";
        }
    }
}