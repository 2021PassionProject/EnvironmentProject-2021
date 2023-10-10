package passion.springboot.passion.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.domain.Board;


import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private JdbcTemplate jdbcTemplate;
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
    public int upload(Board board) {
        return this.jdbcTemplate.update("INSERT INTO board(board_id, title, content) VALUES(seq_member.nextval,?,?)",board.getTitle(),board.getContent());
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
