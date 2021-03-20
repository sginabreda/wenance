package com.wenance.challenge.application.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import java.util.Collections

@Configuration
class BuenBitConfiguration : BaseConfiguration() {

    @Bean
    fun buenBitWebClient(
        @Value("\${services.buenBit.host}") host: String
    ): WebClient {

        return WebClient
            .builder()
            .baseUrl(host)
            .defaultHeaders { headers ->
                headers.setAll(
                    (
                            hashMapOf(
                                HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON_VALUE
                            )
                            )
                )
            }
            .defaultUriVariables(Collections.singletonMap("url", host))
            .filters { listOf(logRequest(), logResponse()) }
            .build()
    }
}