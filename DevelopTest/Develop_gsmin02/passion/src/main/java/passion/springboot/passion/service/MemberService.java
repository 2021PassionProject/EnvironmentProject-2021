package passion.springboot.passion.service;

import passion.springboot.passion.domain.*;

import java.util.List;

public interface MemberService {
    Member getMember(long id);
    Member getMemberByEmail(String email);

    Board getBoard(long id);
    int riseView(Long id);

    List<Board> getBoards();

    List<Member> getMembers();
    List<Member> getMembersByPage(int index, int size);
    int postComment(Comment comment);
    int postMember(Member member);
    int postBoard(Board board, Member member);
    int editBoard(Board board);
    int putMember(Member member);
    int deleteMember(Member member);
}
