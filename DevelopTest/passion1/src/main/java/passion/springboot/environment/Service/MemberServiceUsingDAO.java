package passion.springboot.environment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.springboot.environment.domain.Member;
import passion.springboot.environment.repository.MemberDAOImpl;

import java.sql.SQLException;

@Service
public class MemberServiceUsingDAO {
    private MemberDAOImpl memberDAO;
    @Autowired
    public MemberServiceUsingDAO(MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }
    public Member getMemberByEmail(String email) throws SQLException {
        Member member = new Member();
        member.setEmail(email);
        return memberDAO.readByEmail(member);
    }

}
