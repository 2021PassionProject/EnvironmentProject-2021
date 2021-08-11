package passion.springboot.passion2021.service;


import passion.springboot.passion2021.domain.Member;

import java.util.List;

public interface MemberService {
    Member getMember(long id);
    Member getMemberByEmail(String email);
    List<Member> getMembers();
    List<Member> getMembersByPage(int index, int size);
    int postMember(Member member);
    int putMember(Member member);
    int deleteMember(Member member);
    public int idCheck(String email);
}

