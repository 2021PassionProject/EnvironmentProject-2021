package passion.springboot.passion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.springboot.passion.domain.Board;
import passion.springboot.passion.domain.Comment;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.repository.MemberRepository;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Autowired  // MemberRepository 유형으로 등록된 객체를 Spring Framework 가 자동 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(long id) {
        Member member = new Member();
        member.setId(id);
        return memberRepository.readById(member);
    }

    @Override
    public Member getMemberByEmail(String email) {
        Member member = new Member();
        member.setEmail(email);
        return memberRepository.readByEmail(member);
    }

    @Override
    public Board getBoard(long id) {
        Board board = new Board();
        board.setBoard_id(id);
        return memberRepository.readByBoard_Id(board);
    }

    @Override
    public int riseView(Long id) {
        return memberRepository.riseByView(id);
    }

    @Override
    public List<Board> getBoards() {
        return memberRepository.readBoards();
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.readMembers();
    }

    @Override
    public List<Member> getMembersByPage(int index, int size) {
        return null;
    }

    @Override
    public int postComment(Comment comment) {
        return memberRepository.replyComment(comment);
    }

    @Override
    public int postMember(Member member) {
        return memberRepository.create(member);
    }

    @Override
    public int postBoard(Board board, Member member) {
        return memberRepository.upload(board, member);
    }

    @Override
    public int editBoard(Board board) {
        return memberRepository.modify(board);
    }

    @Override
    public int putMember(Member member) {
        return memberRepository.update(member);
    }

    @Override
    public int deleteMember(Member member) {
        return memberRepository.delete(member);
    }
}
