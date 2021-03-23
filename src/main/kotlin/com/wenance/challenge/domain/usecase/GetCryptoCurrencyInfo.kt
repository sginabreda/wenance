package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
import com.wenance.challenge.logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.stream.Collectors

class GetCryptoCurrencyInfo(
    private val buenBitGateway: BuenBitGateway,
    private val cryptoCurrencyRepository: BuenBitRepository
) {
    private val log by logger()

    operator fun invoke(): Mono<MutableList<CryptoCurrencyInfo>> {
        return buenBitGateway.listCryptoCurrencyInfo()
            .flatMapMany { Flux.fromIterable(it) }
            .doOnNext { log.info("Working on ${it.cryptoCurrency} ${it.currency}") }
            .flatMap { saveDetail(it) }
            .collect(Collectors.toList())
    }

    private fun saveDetail(detail: CryptoCurrencyInfo): Mono<CryptoCurrencyInfo> {
        return cryptoCurrencyRepository.insert(detail)
    }
}
