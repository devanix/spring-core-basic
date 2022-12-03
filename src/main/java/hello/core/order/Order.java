package hello.core.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 주문 엔티티
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    /**
     * 최종 할인 정책 계산된 금액
     * @return
     */
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
