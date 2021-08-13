package passion.springboot.passion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.repository.MemberRepositoryImpl;
import passion.springboot.passion.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.AttributedString;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

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
    public String post(Model model) {

        model.addAttribute("board", MemberRepositoryImpl.jdbcTemplate.query("SELECT * FROM board",
                new RowMapper<Board>() {
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();
                        board.setBoard_id(rs.getLong("board_id"));
                        board.setTitle(rs.getString("title"));
                        board.setContent(rs.getString("content"));
                        return board;
                    }
                }
            )
        );

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
