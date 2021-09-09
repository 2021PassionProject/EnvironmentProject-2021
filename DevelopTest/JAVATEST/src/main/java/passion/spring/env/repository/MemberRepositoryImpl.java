package passion.spring.env.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import passion.spring.env.domain.Board;
import passion.spring.env.domain.Comment;
import passion.spring.env.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    public static JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);    // Spring Bean dataSource 객체를 주입해야함.
    }

    // 아이디 중복체크
    @Override
    public int idCheck(String email) {
        String query = "SELECT COUNT(email) FROM member WHERE email = '" + email + "'";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    // 회원가입
    @Override
    public int create(Member member) {
        String query = "insert into member(id, email, pw, name, birth, phone, address, address2) values" + "(seq_member.nextval,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(query,
                member.getEmail(), member.getPw(), member.getName(), member.getBirth(), member.getPhone(), member.getPostcode(), member.getAddress(), member.getAddress2());
    }

    // 게시물 생성
    @Override
    public int upload(Board board, Member member) {
        return this.jdbcTemplate.update("INSERT INTO board VALUES(seq_board.nextval, ?, ?, ?, TO_CHAR(SYSDATE,'yyyy/mm/dd'),0,?)", board.getWriter(), board.getWriter_email(), board.getTitle(), board.getContent());
    }

    // 게시물 수정
    @Override
    public int modify(Board board) {
        return jdbcTemplate.update("UPDATE BOARD SET title =?, content=? where board_id=?", board.getTitle(), board.getContent(), board.getBoard_id());
    }

    @Override
    public int replyComment(Comment comment) {
        return jdbcTemplate.update("INSERT INTO REPLY_COMMENT VALUES(seq_comment.nextval, ?,?,?,to_char(SYSDATE, 'yyyy/mm/dd'))", comment.getBoard_id(), comment.getMember_name(), comment.getContent());
    }

    // 마이페이지
    @Override
    public Member readById(Member member) {
        String query = "select * from member where id='" + member.getId() + "'";
        Object[] id = new Object[]{member.getId()};
        try {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Member>(Member.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // 로그인
    @Override
    public Member readByEmail(Member member) {
        String query = "select * from member where email=?";
        Object[] email = new Object[]{member.getEmail()};

        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                Member member1 = new Member();
                member1.setId(rs.getLong("id"));
                member1.setEmail(rs.getString("email"));
                member1.setPw(rs.getString("pw"));
                member1.setName(rs.getString("name"));
                member1.setBirth(rs.getString("birth"));
                member1.setPhone(rs.getString("phone"));
                member1.setAddress(rs.getString("address"));
                member1.setAddress2(rs.getString("address2"));
                return member1;
            }, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Board readByBoard_Id(Board board) {
        String query = "select * from board where BOARD_ID=?";
        Object[] id = new Object[]{board.getBoard_id()};    // email -> id
        try {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Board>(Board.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int riseByView(Long id) {
        try {
            return jdbcTemplate.update("UPDATE BOARD SET views = views + 1 where BOARD_ID = ?", id);
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
        String query = "select * from member";
        try {
            return jdbcTemplate.query(query, (rs, rowNum) -> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setEmail(rs.getString("email"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                return member;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int update(Member member) {
        String query = "update member set email=?, pw=?, name=?, phone=?, address=?, address2=? where id=?";
        return jdbcTemplate.update(query, member.getEmail(), member.getPw(), member.getName(), member.getPhone(), member.getAddress(), member.getAddress2(), member.getId());
    }

    @Override
    public int delete(Member member) {
        return jdbcTemplate.update(
                "delete from member where id = ?",
                member.getId()
        );
    }
}
