package passion.spring.environment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import passion.spring.environment.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MemberRepositoryImpl implements MemberRepository {
    /*
    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    */

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);    // Spring Bean dataSource 객체를 주입해야함.
    }

    @Override
    public int create(Member member) {
        String query = "insert into member(id, email, pw, name, phone, address) values(seq_member.nextval, '?', '?', '?' , '?', '?')";
        return jdbcTemplate.update(query,
                member.getEmail(), member.getPw(), member.getName(), member.getPhone(), member.getAddress());
    }

    @Override
    public Member readById(Member member) {
        String query = "select * from member where id=?";
        Object[] id = new Object[]{member.getId()};
        try {
            return jdbcTemplate.queryForObject(query,
                    new BeanPropertyRowMapper<Member>(Member.class),
                    id);
            /*
            return jdbcTemplate.queryForObject(query, new RowMapper<Member>() {
                @Override
                public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Member members = new Member();
                    members.setId(rs.getLong("id"));
                    members.setEmail(rs.getString("email"));
                    members.setPw(rs.getString("pw"));
                    members.setName(rs.getString("name"));
                    members.setPhone(rs.getString("phone"));
                    members.setAddress(rs.getString("address"));
                    return members;
                }
            } ,)*/
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Member readByEmail(Member member) {
        String query = "select * from member where email=?";
        Object[] email = new Object[]{member.getEmail()};
        // return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Member>(Member.class), email);
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
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Member> readMembers() {
        String query = "select * from member";
        try {
            return jdbcTemplate.query(query, new RowMapper<Member>() {
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
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int update(Member member) {
        String query = "update member set email=?, pw=?, name=?, phone=?, address=?, where id=?";
        return jdbcTemplate.update(query, member.getEmail(), member.getPw(), member.getName(), member.getPhone(), member.getAddress(), member.getId());
    }

    @Override
    public int delete(Member member) {
        return jdbcTemplate.update(
                "delete from member where id = ?",
                member.getId()
        );
    }
}
