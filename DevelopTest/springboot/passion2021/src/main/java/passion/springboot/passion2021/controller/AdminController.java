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
    private KakaoService kakaoService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }
    @RequestMapping("/ad-index")
    public String adindex() {
        return "admin/ad-index"; //main 폼
    }

    @RequestMapping("/ad-member")
    public String admember(Model model) {
        List<Member> memberList = memberService.getMembers();
        model.addAttribute("memberList", memberList);
        return "admin/ad-member"; //회원정보 폼
    }


}
