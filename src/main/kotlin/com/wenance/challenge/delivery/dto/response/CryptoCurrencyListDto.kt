package com.wenance.challenge.delivery.dto.response

import java.math.BigDecimal

data class CryptoCurrencyListDto(
    val cryptoCurrencies: List<CryptoCurrencyDto>,
    val date: String
) {
    data class CryptoCurrencyDto(
        val cryptoCurrency: String,
        val currency: String,
        val purchasePrice: BigDecimal,
        val sellingPrice: BigDecimal,
        val marketId: String
    )
}
