package passion.springboot.passion2021.service;

import passion.springboot.passion2021.domain.Board;
import passion.springboot.passion2021.domain.Comment;
import passion.springboot.passion2021.domain.Member;

import java.util.List;

public interface BoardService {
    Board getBoard(long id);
    int riseView(Long id);
    List<Board> getBoards();
    int postComment(Comment comment);
    int postBoard(Board board, Member member);
    int editBoard(Board board);
}
