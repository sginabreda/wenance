package com.wenance.challenge.domain.entity

import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import java.math.BigDecimal

data class CryptoCurrencyInfo(
    val cryptoCurrency: CryptoCurrencyCode,
    val currency: CurrencyCode,
    val purchasePrice: BigDecimal,
    val sellingPrice: BigDecimal,
    val marketId: String
)
