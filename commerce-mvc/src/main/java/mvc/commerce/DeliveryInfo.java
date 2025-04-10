package mvc.commerce;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryInfo {

    private int orderId; // 주문 ID
    private String status; // 배송 상태 (예: 배송중, 배송완료, 배송지연)

}
