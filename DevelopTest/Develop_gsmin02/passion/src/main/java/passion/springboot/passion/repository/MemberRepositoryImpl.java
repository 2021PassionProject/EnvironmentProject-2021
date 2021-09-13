package passion.springboot.passion.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        String query = "insert into member(id, email, pw, name, birth, phone, postcode, address, address2) values" + "(seq_member.nextval,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(query,
                member.getEmail(), member.getPw(), member.getName(), member.getBirth(), member.getPhone(), member.getPostcode(), member.getAddress(), member.getAddress2());
    }

    // 마이페이지
    @Override
    public Member readById(Member member) {
        String query = "select * from member where id=?";
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
        String query ="select  * from member where email=?";
        Object[] email = new Object[] {member.getEmail()};

        try {
            return jdbcTemplate.queryForObject(query, new RowMapper<Member>() {
                        @Override
                        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Member member = new Member();
                            member.setId(rs.getLong("id"));
                            member.setEmail(rs.getString("email"));
                            member.setPw(rs.getString("pw"));
                            member.setName(rs.getString("name"));
                            member.setBirth(rs.getString("birth"));
                            member.setPhone(rs.getString("phone"));
                            member.setPostcode(rs.getString("postcode"));
                            member.setAddress(rs.getString("address"));
                            member.setAddress2(rs.getString("address2"));
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
                    member.setBirth(rs.getString("birth"));
                    member.setPhone(rs.getString("phone"));
                    member.setPostcode(rs.getString("postcode"));
                    member.setAddress(rs.getString("address"));
                    member.setAddress2(rs.getString("address2"));
                    return member;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int update(Member member) {
        String query = "update member set email=?, pw=?, name=?, phone=?, postcode=?, address=?, address2=? where id=?";
        return jdbcTemplate.update(query, member.getEmail(), member.getPw(), member.getName(), member.getPhone(), member.getPostcode(), member.getAddress(), member.getAddress2(), member.getId());
    }

    @Override
    public int delete(Member member) {
        return jdbcTemplate.update(
                "delete from member where id = ?",
                member.getId()
        );
    }
}
