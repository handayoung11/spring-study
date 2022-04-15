package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.dto.CreateMember;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createMember() {
        return "members/newMember";
    }

    @PostMapping("/members/new")
    public String createMember(CreateMember member) {
        memberService.join(new Member(member.getName()));
        return "redirect:/";
    }
}
