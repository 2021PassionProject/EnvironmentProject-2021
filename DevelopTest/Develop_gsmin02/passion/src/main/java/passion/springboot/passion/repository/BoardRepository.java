package passion.springboot.passion.repository;

import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Comment;
import passion.springboot.passion.domain.Member;

import java.util.List;

public interface BoardRepository {
    int upload(Board board, Member member);    // 게시물 생성
    int modify(Board board);    // 게시물 수정
    int replyComment(Comment comment);
    Board readByBoard_Id(Board board);
    int riseByView(Long id);
    List<Board> readBoards();
}
