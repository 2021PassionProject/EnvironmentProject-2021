package passion.springboot.passion.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import passion.springboot.passion.domain.Member;

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
        return 0;
    }

    @Override
    public Member readById(Member member) {
        return null;
    }

    @Override
    public Member readByEmail(Member member) {
        String query = "select * from member where email=?";
        Object[] email = new Object[]{member.getEmail()}; // email -> id
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Member>(Member.class), email); // email -> id
    }

    @Override
    public List<Member> raedMembers() {
        return null;
    }

    @Override
    public int update(Member member) {
        return 0;
    }

    @Override
    public int deldete(Member member) {
        return 0;
    }
}
