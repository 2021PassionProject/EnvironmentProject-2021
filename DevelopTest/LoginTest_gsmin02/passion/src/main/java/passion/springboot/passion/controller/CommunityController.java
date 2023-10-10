package passion.springboot.passion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class CommunityController {

    MemberService memberService;
    HttpSession session = null;

    @Autowired
    public CommunityController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/edit")
    public String edit() {
        return "community/edit";
    }

    @GetMapping("/post")
    public String post() {
        return "community/post";
    }

    @GetMapping("/view")
    public String view() {
        return "community/view";
    }

    @GetMapping("/write")
    public String write(HttpServletRequest request) {
        session = request.getSession();
        if(session.getAttribute("id") != null) {
            return "community/write";
        }
        else {
            return "member/login";
        }
    }
    @PostMapping("/upload")
    public String upload(@Valid Board board, HttpServletRequest request) {
        session = request.getSession();
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        board.setTitle(title);
        board.setContent(content);

        if(session.getAttribute("id") != null) {
            if(memberService.postBoard(board) > 0) {
                return "community/post";
            }
            else {
                return "member/login";
            }
        }
        else {
            return "member/login";
        }

    }
}
