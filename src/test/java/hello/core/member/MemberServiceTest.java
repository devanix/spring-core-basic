package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("회원 가입 및 회원 조회하여 비교 테스트")
    void join() {
        // given
        Member member = new Member(1L, "Luka", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}