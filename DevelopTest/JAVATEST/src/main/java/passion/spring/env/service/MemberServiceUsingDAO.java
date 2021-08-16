package passion.spring.env.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import passion.spring.env.domain.Member;
import passion.spring.env.repository.MemberDAOImpl;

import java.sql.SQLException;

@Service
public class MemberServiceUsingDAO extends MemberDAOImpl{
    private MemberDAOImpl memberDAO;
    @Autowired
    public MemberServiceUsingDAO(@Qualifier("memberDAOImpl") MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }
    public Member getMemberByEmail(String email) throws SQLException {
        Member member = new Member();
        member.setEmail(email);
        return memberDAO.readByEmail(member);
    }
}
