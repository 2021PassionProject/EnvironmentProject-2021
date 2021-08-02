package passion.springboot.passion.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import passion.springboot.passion.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Member readById(Member member) {
        return null;
    }

    @Override
    public Member readByEmail(Member member) {
        String query = "select * from Member where email=?";
        Object[] email = new Object[]{member.getEmail()}; //id
        try {
            return jdbcTemplate.queryForObject(query, new RowMapper<Member>() {
                        @Override
                        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Member member = new Member();
                            member.setId(rs.getLong("id"));
                            member.setEmail(rs.getString("email"));
                            member.setPw(rs.getString("pw"));
                            member.setName(rs.getString("name"));
                            member.setPhone(rs.getString("phone"));
                            member.setAddress(rs.getString("address"));
                            return member;
                        }
                    }
                    , email);
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
