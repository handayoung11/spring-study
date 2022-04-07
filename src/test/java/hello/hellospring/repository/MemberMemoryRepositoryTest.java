package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemberMemoryRepositoryTest {

    MemberMemoryRepository memberRepository = new MemberMemoryRepository();

    @AfterEach
    public void clearData() {
        memberRepository.clearStore();
    }

    @Test
    void saveAndFindById() {
        Member member = new Member("member");
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(NullPointerException::new);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findByName() {
        Member member1 = new Member("member"), member2 = new Member("member");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findByName("member");
        assertThat(members.size() >= 2).isTrue();
    }

    @Test
    void findAll() {
        Member member1 = new Member("member"), member2 = new Member("member");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();
        assertThat(members.size() >= 2).isTrue();
    }
}