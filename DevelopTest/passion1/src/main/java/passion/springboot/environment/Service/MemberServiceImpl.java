package passion.springboot.environment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.validation.annotation.Validated;
import passion.springboot.environment.domain.Member;
import passion.springboot.environment.domain.Comment;
import passion.springboot.environment.domain.Board;
import passion.springboot.environment.repository.MemberRepository;


import java.util.List;
//@Validated
@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Override
    public Member getMember(long id) {
        Member member = new Member();
        member.setId(id);
        return memberRepository.readByEmail(member);
    }

    @Override
    public Member getMemberByEmail(String email) {
        Member member = new Member();
        member.setEmail(email);
        return memberRepository.readByEmail(member);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.readMembers();
    }

    @Override
    public List<Member> getMemberByPage(int index, int size) {
        return null;
    }

    @Override
    public int postMember(Member member) {
        return memberRepository.create(member);
    }

    @Override
    public int putMember(Member member) {
        return memberRepository.update(member);
    }

    @Override
    public int deleteMember(Member member) {
        return memberRepository.delete(member);
    }
    @Override
    public int idCheck(String email) {
        int cnt = memberRepository.idCheck(email);
        return cnt;
    }
}
