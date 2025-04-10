package dev.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CommerceWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceWebfluxApplication.class, args);
	}

	// 비동기 요청을 수행하는 클라이언트 객체를 스프링 빈으로 등록
	@Bean
	public WebClient webClient() {
		String delivery_webflux_url = "http://localhost:9010";
		return WebClient.builder()
				.baseUrl(delivery_webflux_url)
				.build();
	}
}
