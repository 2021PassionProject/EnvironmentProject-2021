package passion.spring.env.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import passion.spring.env.domain.Board;
import passion.spring.env.domain.Comment;
import passion.spring.env.domain.Member;
import passion.spring.env.repository.MemberRepositoryImpl;
import passion.spring.env.service.BoardService;
import passion.spring.env.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class CommunityController {

    MemberService memberService;
    BoardService boardService;
    HttpSession session = null;

    @Autowired
    public CommunityController(MemberService memberService, BoardService boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
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

        if (boardService.editBoard(new_board) > 0) {
            return "community/move_post";
        } else {
            return "main/move_index";
        }
    }

    @GetMapping("/post")
    public String post(@RequestParam("list") int list, Model model) {
        int num = 0;
        final long[] max = {0, 0, 0}, max1 = {0}, max2 = {0}, max3 = {0};

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

                        if(max[0] <= rs.getLong("views")) {
                            max[2] = max[1]; max3[0] = max2[0];
                            max[1] = max[0]; max2[0] = max1[0];

                            max[0] = rs.getLong("views");
                            max1[0] = rs.getLong("board_id");
                        }
                        else if (max[1] < rs.getLong("views")) {
                            max[2] = max[1]; max3[0] = max2[0];

                            max[1] = rs.getLong("views");
                            max2[0] = rs.getLong("board_id");
                        }
                        else if(max[2] < rs.getLong("views")) {
                            max[2] = rs.getLong("views");
                            max3[0] = rs.getLong("board_id");
                        }
                        return board;
                    }
                }
                )
        );

        model.addAttribute("max1",max1[0]);
        model.addAttribute("max2",max2[0]);
        model.addAttribute("max3",max3[0]);
        return "community/post";
    }

    @GetMapping("/move_post")
    public String move_post() {
        return "community/move_post";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") long id, Model model, HttpServletRequest request) {
        session = request.getSession();
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("num_id", (int) id);
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
        boardService.riseView(id);

        return "community/view";
    }

    @PostMapping("/reply")
    public String reply(@Valid Model model, Comment comment, HttpServletRequest request) {
        session = request.getSession();
        String member_name = (String) session.getAttribute("name");
        String content = request.getParameter("content");
        String id = request.getParameter("id");

        if (session.getAttribute("userInfo") != null) {
            member_name = (String) session.getAttribute("googleName");
        } else if (session.getAttribute("currentAT") != null) {
            member_name = (String) session.getAttribute("naverName");
        } else if (session.getAttribute("userEmail") != null) {
            member_name = (String) session.getAttribute("nickname");
        }

        int num_id = Integer.parseInt(id);
        model.addAttribute("num_id", num_id);
        Long long_id = (long) num_id;

        comment.setBoard_id(long_id);
        comment.setMember_name(member_name);
        comment.setContent(content);

        if ((session.getAttribute("id") != null) || (session.getAttribute("userInfo") != null) || (session.getAttribute("currentAT") != null) || (session.getAttribute("userEmail") != null)) {
            if (boardService.postComment(comment) > 0) {
                return "community/move_view";
            } else {
                return "member/move_login";
            }
        } else {
            return "member/move_login";
        }
    }

    @GetMapping("/write")
    public String write(HttpServletRequest request) {
        session = request.getSession();
        if ((session.getAttribute("id") != null) || (session.getAttribute("userInfo") != null) || (session.getAttribute("currentAT") != null) || (session.getAttribute("userEmail") != null)) {
            return "community/write";
        } else {
            return "member/move_login";
        }
    }

    @PostMapping("/upload")
    public String upload(@Valid Board board, Member member, HttpServletRequest request) {
        session = request.getSession(true);
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 그냥 로그인 했을 때
        String writer = (String) session.getAttribute("name");
        String writer_email = (String) session.getAttribute("email");

        if (session.getAttribute("userInfo") != null) {
            writer = (String) session.getAttribute("googleName");
            writer_email = (String) session.getAttribute("googleEmail");
        } else if (session.getAttribute("currentAT") != null) {
            writer = (String) session.getAttribute("naverName");
            writer_email = (String) session.getAttribute("naverEmail");
        } else if (session.getAttribute("userEmail") != null) {
            writer = (String) session.getAttribute("nickname");
            writer_email = (String) session.getAttribute("userEmail");
        }

        board.setTitle(title);
        board.setWriter(writer);
        board.setWriter_email(writer_email);
        board.setContent(content);

        if ((session.getAttribute("id") != null) || (session.getAttribute("userInfo") != null) || (session.getAttribute("currentAT") != null) || (session.getAttribute("userEmail") != null)) {
            if (boardService.postBoard(board, member) > 0) {
                return "community/move_post";
            } else {
                return "member/move_login";
            }
        } else {
            return "member/move_login";
        }

    }
}
