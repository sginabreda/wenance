package com.wenance.challenge.infrastructure.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.infrastructure.repository.WenanceRepository
import com.wenance.challenge.util.applyPagination
import java.time.ZonedDateTime

class WenanceGatewayImpl(private val wenanceRepository: WenanceRepository) : WenanceGateway {

    override fun getBitcoinFromDate(fromDate: ZonedDateTime): List<CryptoCurrencyInfo> {
        val fromDateEpoch = fromDate.toEpochSecond()

        return wenanceRepository.getBitcoinFromDate(fromDateEpoch)
    }

    override fun listCryptoCurrencies(
        cryptoCurrency: CryptoCurrencyCode,
        currency: CurrencyCode?,
        fromDate: ZonedDateTime,
        toDate: ZonedDateTime
    ): List<CryptoCurrencyInfo> {
        val fromDateEpoch = fromDate.toEpochSecond()
        val toDateEpoch = toDate.toEpochSecond()

        return wenanceRepository.listCryptoCurrencies(cryptoCurrency, currency, fromDateEpoch, toDateEpoch)
    }

    override fun listResults(
        fromDate: ZonedDateTime?,
        toDate: ZonedDateTime?,
        limit: Int?,
        offset: Int?
    ): List<CryptoCurrencyInfo> {
        val limitValue = limit ?: defaultLimit
        val offsetValue = offset ?: defaultOffset
        val fromDateEpoch = fromDate?.toEpochSecond()
        val toDateEpoch = toDate?.toEpochSecond()

        return wenanceRepository.listResults(fromDateEpoch, toDateEpoch)
            .applyPagination(limitValue, offsetValue)
    }

    companion object {
        private const val defaultLimit = 10
        private const val defaultOffset = 0
    }
}