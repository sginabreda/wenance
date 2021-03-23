package com.wenance.challenge.infrastructure.mapper

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.infrastructure.client.buenbit.dto.BuenBitResponse
import com.wenance.challenge.infrastructure.data.CryptoCurrencyValues
import com.wenance.challenge.infrastructure.data.CurrencyValues
import java.time.Instant

private val cryptoCurrencyMapper = mapOf(
    CryptoCurrencyValues.dai to CryptoCurrencyCode.DAI,
    CryptoCurrencyValues.bitcoin to CryptoCurrencyCode.BITCOIN
)

private val currencyMapper = mapOf(
    CurrencyValues.ars to CurrencyCode.ARS,
    CurrencyValues.usd to CurrencyCode.USD
)

fun BuenBitResponse.BuenBitData.BuenBitDetail.toCryptoCurrencyInfo(): CryptoCurrencyInfo {
    return CryptoCurrencyInfo(
        cryptoCurrency = cryptoCurrencyMapper.getValue(bidCurrency),
        currency = currencyMapper.getValue(askCurrency),
        purchasePrice = purchasePrice.toBigDecimal(),
        sellingPrice = sellingPrice.toBigDecimal(),
        marketId = marketIdentifier,
        timestamp = Instant.now().toEpochMilli()
    )
}
