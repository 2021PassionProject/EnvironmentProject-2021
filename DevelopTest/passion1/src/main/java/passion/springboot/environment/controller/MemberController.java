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
import java.io.IOException;
import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

@Controller
@RequestMapping("/")
public class MemberController {

    // Controller : Post, Get, Put, Delete 메서드
    // Repository or DAO : Create, Read(One, List), Update, Delete
    HttpSession session = null;

    MemberService memberService;

    // @Autowired
    // private KakaoService kakaoService;
    // 지금 보니까 추가가 안된 파일들이 많이 있네요
    // 덮어도 되나요????? 그 추가 안된거 소셜로그인 말씀하시는거죠??
    // 지금 전체적으로 파일이 빠져있네요
    // 정수님 파일에 지금 카카오랑 구글 연동되어 있어서
    // 하나 추가하려면 파일 다 끌고 와야되요
    // 그러면 기존에 있는 내용은 다 없어져서... 어떻게 할까요? 이거 통채로 복사 해놓고 덮을게요!

    public MemberController(MemberService memberService) {
        this.memberService = memberService; // 오른쪽 memberService 객체는 등록된 객체를 주입
    }

    // 마이 페이지
    @GetMapping("/mypage")      // url에는 mypage라는 이름으로 접속
    public String myPage() {
        return "member/mypage";
    }   // member 디렉토리에 있는 mypage 접근

    // 회원가입 페이지
    @GetMapping("/signupPage")      // url에는 signupPage 라는 이름으로 접속
    public String signupPage() {
        return "member/signupPage";
    }   // member 디렉토리에 있는 signup 접근

    // 회원가입 처리
    @PostMapping("/signup") // 정보추가 : PostMapping(보안에 좋음), 수정은 PutMapping, 삭제는 DeleteMapping
    public String signup(@Valid Member member, HttpServletRequest request, Model model) {
        session = request.getSession();
        model.addAttribute("member", session);

        if (memberService.postMember(member) > 0) {
            return "member/loginPage";
        } else {
            return "member/move_signup";
        }
    }

    // 아이디 중복 체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("email") String email) {
        // 전달 받은 email을 가지고 memberService의 idCheck로 이동하여 리턴받은 결과를 int cnt에 저장
        int cnt = memberService.idCheck(email);
        return cnt;
    }

    // 로그인 페이지
    @GetMapping("/loginPage")   // url에는 loginPage 이라는 이름으로 접속
    public String loginPage() {
        return "member/loginPage";
    }  // member 디렉토리에 있는 loginPage 접근

    // 로그인 처리
    @PostMapping("/login")  // 정보추가 : PostMapping(보안에 좋음), 수정 : PutMapping, 삭제 : DeleteMapping
    public String login(HttpServletRequest request, Model model) {
        session = request.getSession();
        String email = request.getParameter("email");
        String pw = request.getParameter("pw");
        // 입력한 email/pw가 서버 쪽에 존재하면 해당 레코드를 객체로 변환, 그렇지 않은 경우 null
        Member retMember = null;

        if ((retMember = memberService.getMemberByEmail(email)) != null && pw.equals(retMember.getPw())) {    // 로그인 성공
            session.setAttribute("id", retMember.getId());
            session.setAttribute("email", retMember.getEmail());
            session.setAttribute("name", retMember.getName());
            //model.addAttribute("member", session.getId());
            return "main/index";
        } else {    // 로그인 실패하면 다시 로그인 창으로 이동
            return "member/loginPage";
        }
    }


    // 카카오톡 로그인
    @RequestMapping("/login-kakao")
    public String loginKakao(@RequestParam("code") String code, HttpSession session) {
        System.out.println("code : " + code);
        String access_Token = kakaoService.getAccessToken(code);
        System.out.println("access_Token : " + access_Token);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);

        System.out.println("login Controller : " + userInfo);
        if (userInfo.get("email") != null) {
            session.setAttribute("nickname",userInfo.get("nickname"));
            session.setAttribute("userEmail", userInfo.get("email"));
            session.setAttribute("access_Token",access_Token);

        }
        return "redirect:/";
    }

    // 마이 페이지
    @GetMapping("/mypage{id}")
    public String getMember(@PathVariable("id") Long id, Model model) {
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "member/mypage";
    }

    // 마이페이지 수정
    @PutMapping("/mypage{id}")
    public String putMember(@PathVariable("id") Long id, @Valid Member member, Model model) {
        if (memberService.putMember(member) > 0) {
            model.addAttribute("member", member);
            return "redirect:/mypage" + id;   // GetMapping("/mypage{id}") 호출
        } else {
            model.addAttribute("message", "업데이트 실패하였습니다.");
            return "member/mypage";
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/mypage{id}")
    public String deleteMember(@Valid Member member, Model model) {
        if (memberService.deleteMember(member) > 0) {
            return "redirect:/logout";
        } else {
            model.addAttribute("message", "탈퇴 실패하였습니다.");
            return "main/index";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logoutMember(HttpServletRequest request) throws IOException {
        session = request.getSession();
        session.invalidate(); //현재 session 객체를 무효화

        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
        }

        return "main/move_index";
    }

    // 카카오 로그아웃
    @RequestMapping(value="/logout2") //카카오 로그아웃
    public String logout(HttpSession session) {
        String access_Token = (String)session.getAttribute("access_Token");

        if(access_Token != null && !"".equals(access_Token)){
            kakaoService.kakaoLogout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userEmail");
            session.invalidate();
        }else{
            System.out.println("access_Token is null");
        }
        return "redirect:/";
    }

//    @GetMapping("")
//    public String getMembers(HttpServletRequest request, Model model) {
//        if (session != null && ((String) session.getAttribute("email")).equals("root@induk.ac.kr")) {
//            List<Member> memberList = memberService.getMembers();
//            model.addAttribute("memberList", memberList);
//            return "main/index";
//        } else {
//            model.addAttribute("message", "관리자로 로그인하시오.");
//            return "member/loginPage";
//        }
//    }

}