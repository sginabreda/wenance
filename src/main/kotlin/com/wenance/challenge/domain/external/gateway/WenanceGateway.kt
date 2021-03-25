package com.wenance.challenge.domain.external.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import java.time.ZonedDateTime

interface WenanceGateway {
    fun getBitcoinFromDate(fromDate: ZonedDateTime): CryptoCurrencyInfo?

    fun listCryptoCurrencies(
        cryptoCurrency: CryptoCurrencyCode,
        currency: CurrencyCode?,
        fromDate: ZonedDateTime,
        toDate: ZonedDateTime
    ): List<CryptoCurrencyInfo>

    fun listResults(
        fromDate: ZonedDateTime?,
        toDate: ZonedDateTime?,
        limit: Int?,
        offset: Int?
    ): List<CryptoCurrencyInfo>
}
