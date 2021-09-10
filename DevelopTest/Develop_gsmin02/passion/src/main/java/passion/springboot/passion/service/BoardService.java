package passion.springboot.passion.service;

import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Comment;
import passion.springboot.passion.domain.Member;

import java.util.List;

public interface BoardService {
    Board getBoard(long id);
    int riseView(Long id);
    List<Board> getBoards();
    int postComment(Comment comment);
    int postBoard(Board board, Member member);
    int editBoard(Board board);
}
