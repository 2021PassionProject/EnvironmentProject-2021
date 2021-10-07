package passion.spring.env.repository;

import passion.spring.env.domain.Board;
import passion.spring.env.domain.Comment;
import passion.spring.env.domain.Member;

import java.util.List;

public interface BoardRepository {
    int upload(Board board, Member member);    // 게시물 생성
    int modify(Board board);    // 게시물 수정
    int replyComment(Comment comment);
    Board readByBoard_Id(Board board);
    int riseByView(Long id);
    List<Board> readBoards();

}
