package passion.springboot.passion.repository;

import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Member;

import java.util.List;

public interface MemberRepository {
    int create(Member member); // 레코드 생성
    int upload(Board board, Member member);

    Member readById(Member member); // 하나 레코드 생성
    Member readByEmail(Member member); // 하나 레코드 생성
    List<Board> readBoards();

    List<Member> readMembers(); // 다수의 레코드
    int update(Member member); // 레코드 수정
    int delete(Member member); // 레코드 삭제
}
