package com.wenance.challenge.infrastructure.client.buenbit

import com.wenance.challenge.infrastructure.client.buenbit.dto.BuenBitResponse
import com.wenance.challenge.logger
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

class BuenBitClient(private val buenBitWebClient: WebClient) {

    private val log by logger()

    fun getBuenBitResponse(): BuenBitResponse? {
        return buenBitWebClient.get()
            .accept(MediaType.APPLICATION_JSON)
            .acceptCharset(Charsets.UTF_8)
            .retrieve()
            .bodyToMono(BuenBitResponse::class.java)
            .timeout(Duration.ofMillis(10_000))
            .doOnError {
                log.error("Error getting BuenBitResponse", it)
            }
            .block()
    }
}
