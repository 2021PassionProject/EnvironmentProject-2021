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

@Controller
@RequestMapping("/")
public class MemberController {

    // Controller : Post, Get, Put, Delete 메서드
    // Repository or DAO : Create, Read(One, List), Update, Delete
    HttpSession session = null;

    MemberService memberService;

    @Autowired  // Spring Framework 가 주입함
    private KakaoService kakaoService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }

    @GetMapping("/mypage{id}") //마이페이지
    public String getMember(@PathVariable("id") Long id, Model model) {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "member/mypage";
    }

    @PutMapping("/mypage{id}")//마이페이지 수정
    public String putMember(@PathVariable("id") Long id, @Valid Member member, Model model) {
        if(memberService.putMember(member) > 0) {
            model.addAttribute("member", member);
            return "redirect:/mypage" + id;  //@GetMapping("/mypage{id}") 호출
        } else {
            model.addAttribute("message", "업데이트를 실패하였습니다.");
            return  "main/index";
        }
    }
    @DeleteMapping("/mypage{id}")
    public String deleteMember(@Valid Member member, Model model) {
        if(memberService.deleteMember(member) > 0) {
            return "redirect:/logout";
        } else {
            model.addAttribute("message", "탈퇴를 실패하였습니다.");
            return "main/index";
        }
    }


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
    @PostMapping("/idCheck") //아이디 중복 체크
    @ResponseBody
    public int idCheck(@RequestParam("email") String email){
        int cnt = memberService.idCheck(email); //전달 받은 email을 가지고 memberService의 idCheck로 이동하여 리턴받은 결과를 int cnt에 저장
        return cnt;
    }


    @RequestMapping("/login-form")
    public String loginform() {
        return "member/login-form"; //로그인폼
    }

    @RequestMapping("/login-kakao") //카카오톡 로그인
    public String loginkakao(@RequestParam("code") String code, HttpSession session) {
        System.out.println("code : " + code);
        String access_Token = kakaoService.getAccessToken(code);
        System.out.println("access_Token : " + access_Token);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }

        return "main/index";
    }
    @RequestMapping(value="/logout2") //카카오 로그아웃
    public String logout(HttpSession session) {
        String access_Token = (String)session.getAttribute("access_Token");

        if(access_Token != null && !"".equals(access_Token)){
            kakaoService.kakaoLogout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
            session.invalidate();
        }else{
            System.out.println("access_Token is null");

        }
        return "redirect:/";
    }

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


    @GetMapping("/logout")
    public String logoutMember(HttpServletRequest request) {    // 로그아웃
        HttpSession session = request.getSession();
        if (session != null)
            session.invalidate(); //현재 session 객체를 무효화
        return "main/index";
    }

}