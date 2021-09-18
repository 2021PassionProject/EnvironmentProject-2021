package passion.springboot.environment.Service;


import passion.springboot.environment.domain.*;

import java.util.List;

public interface MemberService {
    Member getMember(long id);
    Member getMemberByEmail(String email);
    List<Member> getMembers();
    List<Member> getMemberByPage(int index, int size);
    int postMember(Member member);
    int putMember(Member member);
    int deleteMember(Member member);
    int idCheck(String email);
}
