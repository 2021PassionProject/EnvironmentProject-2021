package passion.spring.environment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import passion.spring.environment.domain.Member;
import passion.spring.environment.repository.MemberDAOImpl;

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
