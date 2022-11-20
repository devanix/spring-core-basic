package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 정액 할인 정책 구현체
 */
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixDiscount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixDiscount;
        } else {
            return 0;
        }
    }
}
