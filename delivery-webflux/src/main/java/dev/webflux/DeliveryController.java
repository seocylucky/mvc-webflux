package dev.webflux;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@RequestMapping(path = "/webflux/delivery")
@RestController
public class DeliveryController {

    // 인메모리 배송 정보 데이터
    private static final Map<Integer, String> deliveryStatusMap = Map.of(
            1001, "배송 중",
            1002, "배송 중",
            1003, "배송 지연",
            1004, "입고",
            1005, "배송 지연"
    );

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @GetMapping("/{orderId}")
    public Mono<DeliveryInfo> getDeliveryInfo(@PathVariable("orderId") int orderId) throws InterruptedException {

        // 3초 지연(MVC와 동일)
        Thread.sleep(3000);

        // 배송 정보를 Mono로 감싸서 비동기 형태로 응답
        String status = deliveryStatusMap.get(orderId);
        DeliveryInfo info = new DeliveryInfo(orderId, status);

        return Mono.just(info); // just(): Mono 객체 생성

    }
}
