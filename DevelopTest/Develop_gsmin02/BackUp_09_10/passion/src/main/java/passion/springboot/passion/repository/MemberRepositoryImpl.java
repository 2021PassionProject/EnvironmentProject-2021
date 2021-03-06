package passion.springboot.passion.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import passion.springboot.passion.domain.Comment;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.domain.Board;


import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    public static JdbcTemplate jdbcTemplate;
    @Autowired
    public MemberRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int create(Member member) {
        String query = "insert into member(id, email, pw, name, birth, phone, address, address2) values"+"(seq_member.nextval,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(query,
                member.getEmail(), member.getPw(), member.getName(), member.getBirth(), member.getPhone(), member.getAddress(),member.getAddress2());
    }

    @Override
    public int upload(Board board, Member member) {
        return jdbcTemplate.update("INSERT INTO board VALUES(seq_board.nextval,?,?,?,TO_CHAR(SYSDATE,'yyyy/mm/dd'),0,?)",board.getWriter(),board.getWriter_email(),board.getTitle(),board.getContent());
    }

    @Override
    public int modify(Board board) {
        return jdbcTemplate.update("UPDATE BOARD SET title=?, content=? where board_id=?", board.getTitle(), board.getContent(), board.getBoard_id());
    }

    @Override
    public int replyComment(Comment comment) {
        return jdbcTemplate.update("INSERT INTO REPLY_COMMENT VALUES(SEQ_COMMENT.nextval,?,?,?,?,TO_CHAR(SYSDATE,'yyyy/mm/dd'))", comment.getBoard_id(), comment.getMember_id(), comment.getMember_name(), comment.getContent());
    }

    @Override
    public Member readById(Member member) {
        return null;
    }

    @Override
    public Member readByEmail(Member member) {
        String query = "select * from member where email=?";
        Object[] email = new Object[]{member.getEmail()}; // email -> id
        try {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Member>(Member.class), email);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Board readByBoard_Id(Board board) {
        String query = "select * from board where BOARD_ID=?";
        Object[] id = new Object[]{board.getBoard_id()}; // email -> id
        try {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Board>(Board.class), id);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int riseByView(Long id) {
        try {
            return jdbcTemplate.update("UPDATE BOARD SET views = views + 1 where board_id = ?", id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public int deleteBoard(long id) {
        try {
            return jdbcTemplate.update("DELETE FROM BOARD WHERE BOARD_ID = ?", id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public int deleteComment(long id) {
        try {
            return jdbcTemplate.update("DELETE FROM REPLY_COMMENT WHERE COMMENT_ID = ?", id);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<Board> readBoards() {
        return null;
    }

    @Override
    public List<Member> readMembers() {
        return null;
    }

    @Override
    public int update(Member member) {
        return 0;
    }

    @Override
    public int delete(Member member) {
        return 0;
    }
}
