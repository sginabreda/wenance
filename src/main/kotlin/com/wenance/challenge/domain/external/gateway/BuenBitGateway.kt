package com.wenance.challenge.domain.external.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo

interface BuenBitGateway {
    fun listCryptoCurrencyInfo(): List<CryptoCurrencyInfo>
}
