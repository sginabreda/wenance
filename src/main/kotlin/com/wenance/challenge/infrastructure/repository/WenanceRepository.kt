package com.wenance.challenge.infrastructure.repository

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode

interface WenanceRepository {
    fun getBitcoinFromDate(timestamp: Long): List<CryptoCurrencyInfo>

    fun listCryptoCurrencies(
        cryptoCurrency: CryptoCurrencyCode,
        currency: CurrencyCode?,
        fromDate: Long,
        toDate: Long
    ): List<CryptoCurrencyInfo>

    fun listResults(fromDate: Long?, toDate: Long?): List<CryptoCurrencyInfo>
}
