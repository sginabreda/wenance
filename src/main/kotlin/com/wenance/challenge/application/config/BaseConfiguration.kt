package com.wenance.challenge.application.config

import com.wenance.challenge.logger
import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import reactor.core.publisher.Mono
import reactor.netty.Connection
import reactor.netty.tcp.TcpClient
import java.util.concurrent.TimeUnit

abstract class BaseConfiguration {

    private val timeout = 20000L
    private val log by logger()

    fun createSslContext(): SslContext {
        return SslContextBuilder
            .forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build()
    }

    fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest: ClientRequest ->
            log.info("Request: ${clientRequest.method()} ${clientRequest.url()}")
            clientRequest.headers()
                .forEach { name, values ->
                    values.forEach { value -> log.info("$name=$value") }
                }
            Mono.just(clientRequest)
        }
    }

    fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
            log.info("Response Status ${clientResponse.statusCode()}")
            Mono.just(clientResponse)
        }
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
