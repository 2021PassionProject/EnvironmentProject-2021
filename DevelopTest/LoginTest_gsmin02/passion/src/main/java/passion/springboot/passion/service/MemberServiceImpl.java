package passion.springboot.passion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import passion.springboot.passion.domain.Member;
import passion.springboot.passion.repository.MemberRepository;

import java.util.List;

@Service
public class MemberServiceImpl implements  MemberService {
    private MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(long id) {
        return null;
    }

    @Override
    public Member getMemberByEmail(String email) {
        Member member = new Member();
        member.setEmail(email); // email -> id
        return memberRepository.readByEmail(member);
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public List<Member> getMembersByPage(int index, int size) {
        return null;
    }

    @Override
    public int postMember(Member member) {
        return 0;
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
