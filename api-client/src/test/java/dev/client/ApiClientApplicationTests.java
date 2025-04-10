package dev.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@SpringBootTest
class ApiClientApplicationTests {

	private static final List<Integer> ORDER_IDS
			= List.of(1001, 1002, 1003, 1004, 1005);
	private static final String MVC_BASE_URL = "http://localhost:8080/mvc/delivery-info/";

	private static final String WEBFLUX_BASE_URL = "http://localhost:9000/webflux/delivery-info/";

	private final HttpClient httpClient = HttpClient.newHttpClient();

	// mvc 방식의 테스트 케이스
	@Test
	void MVC_요청() throws Exception {
		Instant start = Instant.now(); // 시작 시간 측정

		// mvc-commerce-service로 여러 번 요청 수행
		for (int orderId : ORDER_IDS) {
			// HttpRequest - 순수 java로 작성된 API Client

			// 요청에 필요한 객체 생성
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(MVC_BASE_URL + orderId))
					.GET()
					.build();

			// 실제 요청 수행
			HttpResponse<String> response // 응답 결과
					= httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			log.info("응답(orderId={}): {}", orderId, response.body());
		}

		Instant end = Instant.now(); // 종료 시간 측정
		log.info("[동기 요청 종료] 총 소요 시간: {}ms",
				Duration.between(start,end).toMillis());
	}

	// webflux 방식의 테스트 케이스
	@Test
	void Webflux_요청() throws Exception {
		log.info("[비동기 요청 시작]");
		Instant start = Instant.now();

		// 비동기적으로 총 5번 요청 수행 시, 결과를 다 받고 출력

		Instant end = Instant.now();
		log.info("[비동기 요청 종료] 총 소요 시간: {}ms", Duration.between(start, end).toMillis());
	}
}
