package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 회원 서비스 구현체
 */
@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 가입
     * @param member
     */
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }


    /**
     * 조회
     * @param memberId
     * @return
     */
    @Override
    public Member findMember(long memberId) {
        return memberRepository.findById(memberId);
    }
}
