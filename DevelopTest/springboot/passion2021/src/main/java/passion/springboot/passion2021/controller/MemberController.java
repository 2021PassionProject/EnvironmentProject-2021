package passion.springboot.passion2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import passion.springboot.passion2021.domain.Member;
import passion.springboot.passion2021.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/mypage")      // url에는 mypage라는 이름으로 접속
    public String myPage() {
        return "member/mypage";
    }   // member 디렉토리에 있는 mypage 접근

    @GetMapping("/signupPage")      // url에는 signupPage 라는 이름으로 접속
    public String signupPage() {
        return "member/signupPage";
    }   // member 디렉토리에 있는 signup 접근
    @PostMapping("/signup") // 정보추가 : PostMapping(보안에 좋음), 수정은 PutMapping, 삭제는 DeleteMapping
    public String signup(@Valid Member member, HttpServletRequest request, Model model) {
        session = request.getSession();
        model.addAttribute("member", session);

        if (memberService.postMember(member) > 0) {
            //model.addAttribute("member", session);
            return "member/login-form";
        }
        else {
            return "member/signupPage";
        }
    }

    @GetMapping("/login-form")   // url에는 loginPage 이라는 이름으로 접속
    public String loginform() {
        return "member/login-form";
    }  // member 디렉토리에 있는 loginPage 접근

    @PostMapping("/login")  // 정보추가 : PostMapping(보안에 좋음), 수정 : PutMapping, 삭제 : DeleteMapping
    public String login(HttpServletRequest request, Model model) {
        session = request.getSession();
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        // 입력한 email/pw가 서버 쪽에 존재하면 해당 레코드를 객체로 변환, 그렇지 않은 경우 null
        Member retMember = memberService.getMemberByEmail(email);

        if (retMember != null && pw.equals(retMember.getPw())) {    // 로그인 성공
            session.setAttribute("id", retMember.getId());
            session.setAttribute("email", retMember.getEmail());
            session.setAttribute("name", retMember.getName());
            model.addAttribute("member", session);
            return "main/index";
        } else {    // 로그인 실패하면 다시 로그인 창으로 이동
            return "member/login-form";
        }
    }

    @GetMapping("/{id}")
    public String getMember(@PathVariable("id") Long id, Model model) {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "member/mypage";
    }

    @PutMapping("/{id}")
    public String putMember(@PathVariable("id") Long id, @Valid Member member, Model model) {
        if (memberService.putMember(member) > 0) {
            model.addAttribute("member", member);
            return "redirect:/" + id;   // GetMapping 호출
        } else {
            model.addAttribute("cookie_id", "업데이트 실패하였습니다.");
            return "member/mypage";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@Valid Member member, Model model) {
        if (memberService.deleteMember(member) > 0) {
            return "redirect:/main/index";
        } else {
            model.addAttribute("message", "탈퇴 실패하였습니다.");
            return "member/mypage";
        }
    }

    @GetMapping("/logout")
    public String logoutMember(HttpServletRequest request) {    // 로그아웃
        HttpSession session = request.getSession();
        if (session != null)
            session.invalidate(); //현재 session 객체를 무효화
        return "main/index";
    }


}