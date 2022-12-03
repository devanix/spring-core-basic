package hello.core.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 엔티티
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {

    private Long id;
    private String name;
    private Grade grade;
}
