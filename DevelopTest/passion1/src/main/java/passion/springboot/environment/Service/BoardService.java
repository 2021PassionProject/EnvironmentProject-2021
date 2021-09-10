package passion.springboot.environment.Service;

import passion.springboot.environment.domain.Board;
import passion.springboot.environment.domain.Comment;
import passion.springboot.environment.domain.Member;

import java.util.List;

public interface BoardService {
    Board getBoard(long id);
    int riseView(Long id);
    List<Board> getBoards();
    int postComment(Comment comment);
    int postBoard(Board board, Member member);
    int editBoard(Board board);
}
