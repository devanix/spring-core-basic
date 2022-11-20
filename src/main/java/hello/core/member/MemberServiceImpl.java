package hello.core.member;

/**
 * 회원 서비스 구현체
 */
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
