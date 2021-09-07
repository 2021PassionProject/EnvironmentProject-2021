package passion.springboot.passion2021.repository;

import passion.springboot.passion2021.domain.*;

import java.util.List;

public interface BoardRepository {
    int upload(Board board, Member member);
    int modify(Board board);
    int replyComment(Comment comment);
    Board readByBoard_Id(Board board);
    int riseByView(Long id);
    List<Board> readBoards();
}