package passion.spring.env.service;

import passion.spring.env.domain.Board;
import passion.spring.env.domain.Member;

import java.util.List;

public interface MemberService {
    Member getMember(long id);
    Member getMemberByEmail(String email);

    List<Member> getMembers();
    List<Member> getMembersByPage(int index, int size);

    int postMember(Member member);
    int postBoard(Board board);

    int putMember(Member member);
    int deleteMember(Member member);
    int idCheck(String email);
}
