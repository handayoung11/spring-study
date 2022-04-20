package hello.hellospring;

import hello.hellospring.repository.MemberJdbcTemplateRepository;
import hello.hellospring.repository.MemberMemoryRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemberMemoryRepository();
        return new MemberJdbcTemplateRepository();
    }
}
