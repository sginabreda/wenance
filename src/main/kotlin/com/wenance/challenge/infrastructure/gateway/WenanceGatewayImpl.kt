package com.wenance.challenge.infrastructure.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.infrastructure.repository.WenanceRepository
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.time.ZonedDateTime

class WenanceGatewayImpl(private val wenanceRepository: WenanceRepository) : WenanceGateway {

    override fun getBitcoinPrice(fromDate: ZonedDateTime): List<CryptoCurrencyInfo> {
        val fromDateEpoch = fromDate.toEpochSecond()
        return wenanceRepository.getBitcoinFromTimestamp(fromDateEpoch)
    }

    override fun getAveragePrice(fromDate: ZonedDateTime, toDate: ZonedDateTime): BigDecimal {
        TODO()
    }

    override fun listResults(fromDate: ZonedDateTime?, toDate: ZonedDateTime?) {
        TODO("Not yet implemented")
    }
}