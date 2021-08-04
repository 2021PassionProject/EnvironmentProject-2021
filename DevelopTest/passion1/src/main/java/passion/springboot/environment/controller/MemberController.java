package passion.springboot.environment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import passion.springboot.environment.Service.MemberService;
import passion.springboot.environment.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

@Controller
@RequestMapping("/")
public class MemberController {
    HttpSession session = null;
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    }

    @GetMapping("/loginPage")
    public String login() {
        return "member/loginPage";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "member/mypage";
    }

    @GetMapping("/signupPage")
    public String signup() {
        return "member/signupPage";
    }
    @PostMapping("/signup") // 정보추가 : PostMapping(보안에 좋음), 수정은 PutMapping, 삭제는 DeleteMapping
    public String signup(@Valid @ModelAttribute("") Member member, HttpServletRequest request, Model model) {
        session = request.getSession();
        model.addAttribute("member", session);

        if (memberService.postMember(member) > 0) {
            return "member/loginPage";
        } else {
            return "member/signupPage";
        }
    }
    @PostMapping("/login")
    public String signin(HttpServletRequest request, Model model) {
        session = request.getSession();
        String email = request.getParameter("email"); // email-> id
        String pw = request.getParameter("pw");
        Member retMember = memberService.getMemberByEmail(email);

        if (retMember != null && retMember.getPw().equals(pw)) { //로그인 성공
            session.setAttribute("id", retMember.getId());
            session.setAttribute("email", retMember.getEmail());
            session.setAttribute("name", retMember.getName());
            model.addAttribute("member", session);
            /*session.setAttribute("id",retMember.getId());*/
            return "main/index";
        } else {
              model.addAttribute("message","로그인 실패");
            /*return "errors/message";*/
            return "member/signupPage";
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
        if(memberService.putMember(member) > 0) {
            model.addAttribute("member", member);
            return "redirect:member" + id; // GetMapping 호출
        } else {
            model.addAttribute("message", "업데이트를 실패하였습니다.");
            return "errors/message";
        }
    }
    @DeleteMapping("/{id}")
    public String deleteMember(@Valid Member member, Model model) {
        if(memberService.deleteMember(member) > 0) {
            return "redirect:/member/logout";
        } else {
            model.addAttribute("message", "탈퇴를 실패하였습니다.");
            return "errors/message";
        }
    }
    @GetMapping("/logout")
    public String signout(HttpServletRequest request){
       if(session != null)
//           session.setAttribute("id", null);
//        session.setAttribute("email", null);
//        session.setAttribute("name", null);
            session.invalidate(); //현재 session 객체를 무효화
        return "main/index";
    }
}
//    @GetMapping("")
//    public String getMembers(HttpServletRequest request, Model model) {
//        if(session != null && ((String) session.getAttribute("email")).equals("root@induk.ac.kr")) {
//            List<Member> memberList = memberService.getMembers();
//            model.addAttribute("memberList", memberList);
//            return "member/list";
//        } else {
//            model.addAttribute("message", "관리자로 로그인하시오.");
//            return "errors/message";
//        }
//    }
//@GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if(authentication != null){
//            new SecurityContextLogoutHandler().logout(request,response,authentication);
//        }
//        return "redirect:/";
//    }