package passion.springboot.passion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.repository.MemberRepositoryImpl;
import passion.springboot.passion.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public String post(@RequestParam("list") long list, Model model) {
        int num = 0;
        model.addAttribute("num", num);
        model.addAttribute("list", list);
        model.addAttribute("board", MemberRepositoryImpl.jdbcTemplate.query("SELECT * FROM board",
                new RowMapper<Board>() {
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();
                        board.setBoard_id(rs.getLong("board_id"));
                        board.setTitle(rs.getString("title"));
                        board.setWriter(rs.getString("writer"));
                        board.setWrite_time(rs.getString("write_time"));
                        board.setViews(rs.getLong("views"));

                        return board;
                    }
                }
            )
        );

        return "community/post";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") long id, Model model) {
        model.addAttribute("num_id", (int)id);
        model.addAttribute("data", MemberRepositoryImpl.jdbcTemplate.query("SELECT * FROM board",
                new RowMapper<Board>() {
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();
                        board.setBoard_id(rs.getLong("board_id"));
                        board.setTitle(rs.getString("title"));
                        board.setWriter(rs.getString("writer"));
                        board.setWrite_time(rs.getString("write_time"));
                        board.setViews(rs.getLong("views"));
                        board.setContent(rs.getString("content"));

                        return board;
                    }
                }
            )
        );

        // memberService.riseView(id);

        return "community/view";
    }

    @GetMapping("/write")
    public String write(HttpServletRequest request) {
        session = request.getSession();
        if(session.getAttribute("id") != null) {
            return "community/write";
        }
        else {
            return "member/move_login";
        }
    }
    @PostMapping("/upload")
    public String upload(@Valid Board board, Member member, HttpServletRequest request) {
        session = request.getSession();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = (String) session.getAttribute("name");

        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);

        if(session.getAttribute("id") != null) {
            if(memberService.postBoard(board, member) > 0) {
                return "community/move_post";
            }
            else {
                return "member/move_login";
            }
        }
        else {
            return "member/move_login";
        }

    }
}
