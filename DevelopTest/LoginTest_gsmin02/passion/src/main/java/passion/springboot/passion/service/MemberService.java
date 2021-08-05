package passion.springboot.passion.service;

import passion.springboot.passion.domain.Member;
import passion.springboot.passion.domain.Board;

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
}
