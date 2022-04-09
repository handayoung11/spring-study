package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberMemoryRepository;
import hello.hellospring.repository.MemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository = new MemberMemoryRepository();

    /**
     * 회원가입
     */
    public Long join(Member member) {
        checkDuplicated(member.getName()); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void checkDuplicated(String name) {
        memberRepository.findByName(name)
                .ifPresent(m -> { throw new IllegalStateException("이미 존재하는 회원입니다."); });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Member findOne(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }
}
