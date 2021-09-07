package passion.springboot.passion.repository;

import passion.springboot.passion.domain.*;

import java.util.List;

public interface MemberRepository {
    int create(Member member); // 레코드 생성
    int upload(Board board, Member member);
    int modify(Board board);

    int replyComment(Comment comment);

    Member readById(Member member); // 하나 레코드 생성
    Member readByEmail(Member member); // 하나 레코드 생성

    Board readByBoard_Id(Board board);
    int riseByView(Long id);
    int deleteBoard(long id);
    int deleteComment(long id);

    List<Board> readBoards();

    List<Member> readMembers(); // 다수의 레코드
    int update(Member member); // 레코드 수정
    int delete(Member member); // 레코드 삭제
}
