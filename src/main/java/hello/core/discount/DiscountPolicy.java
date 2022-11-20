package hello.core.discount;

import hello.core.member.Member;

/**
 * 할인 정책 인터페이스
 */
public interface DiscountPolicy {
    /**
     * 할인 대상 금액
     *   - 회원 정보와 가격을 넘겨 얼마를 할인해 줄지 알아서 계산해 준다.
     * @param member
     * @param price
     * @return
     */
    int discount(Member member, int price);
}
