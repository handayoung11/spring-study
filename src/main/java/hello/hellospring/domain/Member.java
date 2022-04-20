package hello.hellospring.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class Member {
    private Long id;
    private String name;

    public Member(String name) {
        this.name = name;
    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void createId(Long id) {
        this.id = id;
    }
}
