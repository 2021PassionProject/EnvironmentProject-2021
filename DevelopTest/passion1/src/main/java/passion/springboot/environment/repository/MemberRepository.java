package passion.springboot.environment.repository;

import passion.springboot.environment.domain.Member;

import java.util.List;

public interface MemberRepository {
    int create(Member member); //레코드 생성
    Member readById(Member member); //하나 레코드 생성
    Member readByEmail(Member member); //하나 레코드 생성
    List<Member> readMembers(); //다수의 레코드
    int update(Member member); //레코드 수정
    int delete(Member member); //레코드삭제
    int idCheck(String email);
}
