package dev.webflux;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequestMapping(path = "/webflux/delivery-info")
@RestController
@RequiredArgsConstructor
public class CommerceController {

    // 비동기 요청 클라이언트
    private final WebClient webClient;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @GetMapping("/{orderId}")
    public Mono<DeliveryInfo> getInfo(@PathVariable int orderId) {
        log.info("요청 시작 시간 - : {}", LocalTime.now().format(TIME_FORMATTER));

        String delivery_serivce_url = "/webflux/delivery/{orderId}";

        return webClient // 실제 요청 수행
                .get()
                .uri(delivery_serivce_url, orderId)
                .retrieve().bodyToMono(DeliveryInfo.class) // Mono 타입으로 변경
                .doOnNext(info // 결과를 응답 받고 처리할 로직(콜백)
                        -> log.info("응답 완료 시간 - : {}", LocalTime.now().format(TIME_FORMATTER)));
    }
}
