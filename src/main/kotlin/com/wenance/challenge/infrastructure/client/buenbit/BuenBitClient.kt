package com.wenance.challenge.infrastructure.client.buenbit

import com.fasterxml.jackson.databind.ObjectMapper
import com.wenance.challenge.infrastructure.client.buenbit.dto.BuenBitResponse
import com.wenance.challenge.logger
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class BuenBitClient(private val buenBitWebClient: WebClient, private val mongoTemplate: MongoTemplate) {

    private val mapper = ObjectMapper()
    private val log by logger()

    @Scheduled(fixedRate = 5000)
    fun getBuenBitResponse() {
        val buenBitResponse = buenBitWebClient.get()
            .accept(MediaType.APPLICATION_JSON)
            .acceptCharset(Charsets.UTF_8)
            .retrieve()
            .bodyToMono(BuenBitResponse::class.java)
            .block()

        log.info(mapper.writeValueAsString(buenBitResponse))
        mongoTemplate.save(buenBitResponse!!)
    }
}
