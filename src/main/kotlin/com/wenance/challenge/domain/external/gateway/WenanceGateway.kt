package com.wenance.challenge.domain.external.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.time.ZonedDateTime

interface WenanceGateway {
    fun getBitcoinPrice(fromDate: ZonedDateTime): Flux<CryptoCurrencyInfo>
    fun getAveragePrice(fromDate: ZonedDateTime, toDate: ZonedDateTime): BigDecimal
    fun listResults(fromDate: ZonedDateTime?, toDate: ZonedDateTime?)
}
