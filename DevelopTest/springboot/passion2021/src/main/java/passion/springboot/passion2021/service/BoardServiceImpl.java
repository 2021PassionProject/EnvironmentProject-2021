package passion.springboot.passion2021.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.springboot.passion2021.domain.Board;
import passion.springboot.passion2021.domain.Comment;
import passion.springboot.passion2021.domain.Member;
import passion.springboot.passion2021.repository.BoardRepository;
import passion.springboot.passion2021.repository.MemberRepository;

import java.util.List;
@Service
public class BoardServiceImpl implements BoardService {
    private BoardRepository boardRepository;

    @Autowired  // MemberRepository 유형으로 등록된 객체를 Spring Framework 가 자동 주입
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board getBoard(long id) {
        Board board = new Board();
        board.setBoard_id(id);
        return boardRepository.readByBoard_Id(board);
    }
    @Override
    public int riseView(Long id) {
        return boardRepository.riseByView(id);
    }

    @Override
    public List<Board> getBoards() {
        return boardRepository.readBoards();
    }

    @Override
    public int postComment(Comment comment) {
        return boardRepository.replyComment(comment);
    }

    @Override
    public int postBoard(Board board, Member member) {
        return boardRepository.upload(board, member);
    }

    @Override
    public int editBoard(Board board) {
        return boardRepository.modify(board);
    }


}
