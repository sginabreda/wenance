package com.wenance.challenge.domain.external.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import reactor.core.publisher.Mono

interface BuenBitGateway {
    fun listCryptoCurrencyInfo(): Mono<List<CryptoCurrencyInfo>>
}
