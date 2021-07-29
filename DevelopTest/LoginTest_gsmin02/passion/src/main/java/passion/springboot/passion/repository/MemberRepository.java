package passion.springboot.passion.repository;

import passion.springboot.passion.domain.Member;

import java.util.List;

public interface MemberRepository {
    int create(Member member);
    Member readById(Member member);
    Member readByEmail(Member member);
    List<Member> raedMembers();
    int update(Member member);
    int deldete(Member member);
}
