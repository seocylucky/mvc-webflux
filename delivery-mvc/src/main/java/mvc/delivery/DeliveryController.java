package mvc.delivery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequestMapping(path = "/mvc/delivery")
@RestController
public class DeliveryController {

    // 인메모리 배송 정보 데이터
    private static final Map<Integer, String> deliveryStatusMap = Map.of(
            1001, "배송 중", // 1001인 주문 id는 배송 중인 상태
            1002, "배송 중", // 1002인 주문 id는 배송 중인 상태
            1003, "배송 지연",
            1004, "입고",
            1005, "배송 지연"
    );

    @GetMapping("/{orderId}")
    public ResponseEntity<DeliveryInfo> getDeliveryInfo(@PathVariable int orderId) throws InterruptedException {

        // 배송 정보 조회의 시간을 의도적으로 지연
        Thread.sleep(3000);

        String status = deliveryStatusMap.get(orderId);

        DeliveryInfo info = new DeliveryInfo(orderId, status);
        return ResponseEntity.ok(info);
    }
}
