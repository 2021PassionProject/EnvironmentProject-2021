package passion.springboot.passion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.service.MemberService;
import passion.springboot.passion.service.MemberServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class MemberController {
    // Controller : Post, Get, Put, Delete 메서드
    // Repository or DAO : Create, Read(One, List), Update, Delete
    HttpSession session = null;

    MemberService memberService;

    @Autowired  // Spring Framework 가 주입함
    public MemberController(MemberService memberService) {
        this.memberService = memberService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "member/mypage";
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/join")
    public String joinMember(@Valid Member member, HttpServletRequest request, Model model) {
        session = request.getSession();
        model.addAttribute("member", session);

        if(memberService.postMember(member) > 0) {
            return "member/login";
        }
        else {
            return "member/move_signup";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/signin")  // 정보추가 : PostMapping(보안에 좋음), 수정 : PutMapping, 삭제 : DeleteMapping
    public String loginMember(HttpServletRequest request) {
        session = request.getSession();
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        // 입력한 email/pw가 서버 쪽에 존재하면 해당 레코드를 객체로 변환, 그렇지 않은 경우 null
        Member retMember = null;

        if ((retMember = memberService.getMemberByEmail(email)) != null && pw.equals(retMember.getPw())) {
            session.setAttribute("id", retMember.getId());
            session.setAttribute("email", email);
            session.setAttribute("name", retMember.getName());
            return "main/move_index";
        } else {
            return "member/move_signup";
        }
    }

    @GetMapping("/logout")
    public String logoutMember(HttpServletRequest request) {
        session = request.getSession();
        session.invalidate();   // 현재 session 객체를 무효화

        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
        }
        return "main/move_index";
    }
}
