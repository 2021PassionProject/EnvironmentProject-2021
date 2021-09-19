package passion.springboot.passion2021.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import passion.springboot.passion2021.domain.Member;
import passion.springboot.passion2021.service.KakaoService;
import passion.springboot.passion2021.service.MemberService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    // Controller : Post, Get, Put, Delete 메서드
    // Repository or DAO : Create, Read(One, List), Update, Delete
    HttpSession session = null;

    MemberService memberService;

    @Autowired  // Spring Framework 가 주입함
    public AdminController(MemberService memberService) {
        this.memberService = memberService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }
    @RequestMapping("/ad-index")
    public String adindex(Model model) {
        List<Member> memberList = memberService.getMembers();
        model.addAttribute("memberList", memberList);
        return "admin/ad-index"; //main 폼
    }

    @RequestMapping("/ad-event")
    public String adevent() {
        return "admin/ad-event";
    }

    @RequestMapping("/ad-upcycling")
    public String adupcycling() {
        return "admin/ad-upcycling";
    }

    @RequestMapping("/ad-charts")
    public String adcharts() {
        return "admin/ad-charts";
    }

    @GetMapping("/ad-upcycling_uploda")
    public String adupcycling_uploda() {
        return "admin/ad-upcycling_uploda"; //main 폼
    }

    @RequestMapping("/ad-member")
    public String admember(Model model) {
        List<Member> memberList = memberService.getMembers();
        model.addAttribute("memberList", memberList);
        return "admin/ad-member"; //회원정보 폼
    }
    @GetMapping("/ad-mypage{id}") //어드민 마이페이지
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
            model.addAttribute("message", "탈퇴를 실패하였습니다.");
            return "admin/ad-member";
        }
    }



}
