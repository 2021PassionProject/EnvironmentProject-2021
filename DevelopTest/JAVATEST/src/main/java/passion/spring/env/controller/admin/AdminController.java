package passion.spring.env.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import passion.spring.env.domain.Member;
import passion.spring.env.service.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {
    // Controller : Post, Get, Put, Delete 메서드
    // Repository or DAO : Create, Read(One, List), Update, Delete
    HttpSession session = null;

    MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;  // 오른쪽 memberService 객체는 등록된 객체를 주입
    }

    @RequestMapping("/ad-index")
    public String adIndex() {
        return "admin/ad-index";    // main 폼
    }

    @RequestMapping("/ad-member")
    public String adMember(Model model) {
        List<Member> memberList = memberService.getMembers();
        model.addAttribute("memberList", memberList);
        return "admin/ad-member";   // 회원정보 폼
    }

    @GetMapping("/ad-mypage{id}")   // admin mypage
    public String getMember(@PathVariable("id") Long id, Model model) {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "admin/ad-mypage";
    }

    @DeleteMapping("/ad-mypage{id}")
    public String deleteMember(@Valid Member member, Model model) {
        if(memberService.deleteMember(member) > 0) {
            return "redirect:/ad-member";
        } else {
            model.addAttribute("message", "탈퇴 실패했습니다.");
            return "admin/ad-member";
        }
    }

    @GetMapping("/ad-news")
    public String newsPage() {
        return "admin/ad-news";
    }
}
