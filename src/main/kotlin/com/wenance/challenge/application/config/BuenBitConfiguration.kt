package com.wenance.challenge.application.config

import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient
import java.util.Collections
import java.util.concurrent.TimeUnit

@Configuration
class BuenBitConfiguration {

    private val timeout = 10000L

    @Bean
    fun buenBitWebClient(
        @Value("\${services.buenBit.host}") host: String
    ): WebClient {

        val writeTimeOut = 4000L
        val readTimeOut = 3000L
        val tcpClient = trustedConnection(createSslContext(), readTimeOut, writeTimeOut)

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
            .clientConnector(ReactorClientHttpConnector(HttpClient.from(tcpClient).followRedirect(true)))
            .defaultUriVariables(Collections.singletonMap("url", host))
            .build()
    }

    fun createSslContext(): SslContext {
        return SslContextBuilder.forClient()
            .sslProvider(io.netty.handler.ssl.SslProvider.JDK)
            .build()
    }

    fun trustedConnection(
        sslContext: SslContext,
        readTimeout: Long = timeout,
        writeTimeout: Long = timeout
    ): TcpClient {
        return connection(readTimeout, writeTimeout)
            .secure { sslContextSpec -> sslContextSpec.sslContext(sslContext) }
    }

    private fun connection(readTimeout: Long, writeTimeout: Long): TcpClient {
        return TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout.toInt())
            .doOnConnected { connection: Connection ->
                connection.addHandlerLast(ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                connection.addHandlerLast(WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS))
            }
    }
}
