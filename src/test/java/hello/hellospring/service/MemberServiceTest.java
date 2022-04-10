package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberMemoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest {

    private final MemberMemoryRepository memberMemoryRepository = new MemberMemoryRepository();
    private final MemberService memberService = new MemberService(memberMemoryRepository);

    @AfterEach
    void clear() {
        memberMemoryRepository.clearStore();;
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member("member");

        //when
        Long savedId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(savedId);
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void 중복회원예외() {
        //given
        Member m1 = new Member("member"), m2 = new Member("member");

        //when
        memberService.join(m1);

        //then
        assertThatThrownBy(() -> memberService.join(m2)).isInstanceOf(IllegalStateException.class);
    }
}