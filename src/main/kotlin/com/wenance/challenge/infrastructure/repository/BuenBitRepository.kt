package com.wenance.challenge.infrastructure.repository

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo

interface BuenBitRepository {
    fun insertRecord(detail: CryptoCurrencyInfo): CryptoCurrencyInfo
}
