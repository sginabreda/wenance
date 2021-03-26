package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
import com.wenance.challenge.logger

class GetCryptoCurrencyInfoUseCase(
    private val buenBitGateway: BuenBitGateway,
    private val buenBitRepository: BuenBitRepository
) {
    private val log by logger()

    operator fun invoke() {
        return buenBitGateway.listCryptoCurrencyInfo()
            .forEach {
                log.info("Working on ${it.cryptoCurrency} ${it.currency}")
                saveDetail(it)
            }
    }

    private fun saveDetail(detail: CryptoCurrencyInfo): CryptoCurrencyInfo {
        return buenBitRepository.insertRecord(detail)
    }
}
