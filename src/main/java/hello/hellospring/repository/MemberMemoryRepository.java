package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
import java.util.stream.Collectors;

public class MemberMemoryRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 1L;

    @Override
    public Member save(Member member) {
        member.createId(sequence++);
        store.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findByName(String name) {
        return store.values().stream().filter(m -> m.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
