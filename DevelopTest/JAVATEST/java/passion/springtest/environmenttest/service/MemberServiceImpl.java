package passion.spring.environment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.spring.environment.domain.Member;
import passion.spring.environment.repository.MemberRepository;

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
    public List<Member> getMembers() {
        return memberRepository.readMembers();
    }

    @Override
    public List<Member> getMembersByPage(int index, int size) {
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
}
