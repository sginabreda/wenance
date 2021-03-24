package com.wenance.challenge.infrastructure.repository

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo

interface WenanceRepository {
    fun getBitcoinFromTimestamp(timestamp: Long): List<CryptoCurrencyInfo>
    fun listBitcoins(fromDate: Long, toDate: Long): List<CryptoCurrencyInfo>
    fun listResults(fromDate: Long?, toDate: Long?): List<CryptoCurrencyInfo>
}
