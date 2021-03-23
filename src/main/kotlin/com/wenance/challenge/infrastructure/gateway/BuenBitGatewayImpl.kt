package com.wenance.challenge.infrastructure.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.infrastructure.client.buenbit.BuenBitClient
import com.wenance.challenge.infrastructure.mapper.toCryptoCurrencyInfo
import com.wenance.challenge.logger
import reactor.core.publisher.Mono

class BuenBitGatewayImpl(private val buenBitClient: BuenBitClient) : BuenBitGateway {

    private val log by logger()

    override fun listCryptoCurrencyInfo(): Mono<List<CryptoCurrencyInfo>> {
        return buenBitClient.getBuenBitResponse()
            .doOnNext { log.info("Working on ${it.data} ") }
            .flatMap {
                Mono.just(
                    listOf(
                        it.data.daiars.toCryptoCurrencyInfo(),
                        it.data.daiusd.toCryptoCurrencyInfo(),
                        it.data.btcars.toCryptoCurrencyInfo()
                ))
            }
    }
}
