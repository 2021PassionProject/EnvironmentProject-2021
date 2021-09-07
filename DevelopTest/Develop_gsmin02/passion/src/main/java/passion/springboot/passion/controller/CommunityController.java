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
import passion.springboot.passion.domain.Comment;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.repository.MemberRepositoryImpl;
import passion.springboot.passion.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;

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
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("num_id", id);
        model.addAttribute("data", MemberRepositoryImpl.jdbcTemplate.query("SELECT * FROM board",
                        new RowMapper<Board>() {
                            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                                Board board = new Board();
                                board.setBoard_id(rs.getLong("board_id"));
                                board.setTitle(rs.getString("title"));
                                board.setWriter(rs.getString("writer"));
                                board.setWriter_email(rs.getString("writer_email"));
                                board.setContent(rs.getString("content"));

                                return board;
                            }
                        }
                )
        );
        return "community/edit";
    }

    @PostMapping("/modify")
    public String modify(@Valid @RequestParam("id") long id, HttpServletRequest request) {
        Board new_board = new Board();
        session = request.getSession();

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        new_board.setBoard_id(id);
        new_board.setTitle(title);
        new_board.setContent(content);

        if(memberService.editBoard(new_board) > 0) {
            return "community/move_post";
        }
        else {
            return "main/move_index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        memberService.deleteBoard(id);
        return "community/move_post";
    }

    @GetMapping("/post")
    public String post(@RequestParam("list") int list, Model model) {
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
    public String view(@RequestParam("id") long id, Model model, HttpServletRequest request) {
        session = request.getSession();
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("num_id", (int)id);
        model.addAttribute("data", MemberRepositoryImpl.jdbcTemplate.query("SELECT * FROM board",
                new RowMapper<Board>() {
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();
                        board.setBoard_id(rs.getLong("board_id"));
                        board.setTitle(rs.getString("title"));
                        board.setWriter(rs.getString("writer"));
                        board.setWriter_email(rs.getString("writer_email"));
                        board.setWrite_time(rs.getString("write_time"));
                        board.setViews(rs.getLong("views"));
                        board.setContent(rs.getString("content"));

                        return board;
                    }
                }
            )
        );
        model.addAttribute("comment", MemberRepositoryImpl.jdbcTemplate.query("SELECT * FROM reply_comment",
                new RowMapper<Comment>() {
                    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Comment comment = new Comment();
                        comment.setComment_id(rs.getLong("comment_id"));
                        comment.setBoard_id(rs.getLong("board_id"));
                        comment.setMember_name(rs.getString("member_name"));
                        comment.setContent(rs.getString("content"));
                        comment.setWrite_time(rs.getString("write_time"));

                        return comment;
                    }
                }
            )
        );

        memberService.riseView(id);

        return "community/view";
    }

    @PostMapping("/reply")
    public String reply(@Valid Model model, Comment comment, HttpServletRequest request) {
        session = request.getSession();
        String member_name = (String) session.getAttribute("name");
        Long member_id = (long) session.getAttribute("id");
        String content = request.getParameter("content");
        String id = request.getParameter("id");

        int num_id = Integer.parseInt(id);
        model.addAttribute("num_id", num_id);
        Long long_id = (long) num_id;

        comment.setMember_id(member_id);
        comment.setBoard_id(long_id);
        comment.setMember_name(member_name);
        comment.setContent(content);

        if(session.getAttribute("id") != null) {
            if(memberService.postComment(comment) > 0) {
                return "community/move_view";
            }
            else {
                return "member/move_login";
            }
        }
        else {
            return "member/move_login";
        }
    }

    @GetMapping("comment_delete")
    public String comment_delete(@RequestParam("id") long id) {
        memberService.deleteComment(id);
        return "community/move_post";
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
        String writer_email = (String) session.getAttribute("email");

        board.setTitle(title);
        board.setWriter(writer);
        board.setWriter_email(writer_email);
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
