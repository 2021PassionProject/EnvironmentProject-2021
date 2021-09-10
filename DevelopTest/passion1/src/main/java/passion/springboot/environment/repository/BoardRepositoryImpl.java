package passion.springboot.environment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import passion.springboot.environment.domain.Board;
import passion.springboot.environment.domain.Comment;
import passion.springboot.environment.domain.Member;
import passion.springboot.environment.repository.BoardRepository;

import javax.sql.DataSource;
import java.util.List;

public class BoardRepositoryImpl implements BoardRepository {
    public static JdbcTemplate jdbcTemplate;
    @Autowired
    public BoardRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 게시물 생성
    @Override
    public int upload(Board board, Member member) {
        return jdbcTemplate.update("INSERT INTO board VALUES(seq_board.nextval,?,?,?,TO_CHAR(SYSDATE,'yyyy/mm/dd'),0,?)", board.getWriter(), board.getWriter_email(), board.getTitle(), board.getContent());
    }

    // 게시물 수정
    @Override
    public int modify(Board board) {
        return jdbcTemplate.update("UPDATE BOARD SET title=?, content=? where board_id=?", board.getTitle(), board.getContent(), board.getBoard_id());
    }

    @Override
    public int replyComment(Comment comment) {
        return jdbcTemplate.update("INSERT INTO REPLY_COMMENT VALUES(SEQ_COMMENT.nextval, ?, ?, ?, TO_CHAR(SYSDATE, 'yyyy/mm/dd'))", comment.getBoard_id(), comment.getMember_name(), comment.getContent());
    }

    @Override
    public Board readByBoard_Id(Board board) {
        String query = "select * from board where BOARD_ID=?";
        Object[] id = new Object[]{board.getBoard_id()}; // email -> id
        try {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Board>(Board.class), id);
        } catch (EmptyResultDataAccessException e) {
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
    public List<Board> readBoards() {
        return null;
    }

}