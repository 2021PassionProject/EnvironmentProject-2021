package passion.springboot.environment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.springboot.environment.domain.Member;
import passion.springboot.environment.repository.MemberRepository;

import java.util.List;

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
        return memberRepository.readById(member);
    }

    @Override
    public Member getMemberByEmail(String email) {
        Member member = new Member();
        member.setEmail(email);
        return memberRepository.readByEmail(member);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.readMember();
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
        return 0;
    }

    @Override
    public int deleteMember(Member member) {
        return 0;
    }
}
