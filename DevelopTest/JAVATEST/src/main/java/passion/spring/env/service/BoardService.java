package passion.spring.env.service;

import passion.spring.env.domain.Board;
import passion.spring.env.domain.Comment;
import passion.spring.env.domain.Member;

import java.util.List;

public interface BoardService {
    Board getBoard(long id);
    int riseView(Long id);
    List<Board> getBoards();
    int postComment(Comment comment);
    int postBoard(Board board, Member member);
    int editBoard(Board board);
}
