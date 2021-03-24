package com.wenance.challenge.delivery.dto.response

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.util.Constants.ARGENTINE_TIME_ZONE
import com.wenance.challenge.util.toDto
import com.wenance.challenge.util.toIsoStringDate
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class CryptoCurrencyListDto(
    val records: Collection<CryptoCurrencyInfoDto>,
) {
    @Suppress("unused")
    constructor(cryptoRecords: List<CryptoCurrencyInfo>) : this(
        records = cryptoRecords.map { it.toDto<CryptoCurrencyInfo, CryptoCurrencyInfoDto>() }
    )

    data class CryptoCurrencyInfoDto(
        val cryptoCurrency: String,
        val currency: String,
        val purchasePrice: BigDecimal,
        val sellingPrice: BigDecimal,
        val marketId: String,
        val timestamp: String
    ) {
        @Suppress("unused")
        constructor(crypto: CryptoCurrencyInfo) : this(
            cryptoCurrency = crypto.cryptoCurrency.name.toLowerCase(),
            currency = crypto.currency.name.toLowerCase(),
            purchasePrice = crypto.purchasePrice,
            sellingPrice = crypto.sellingPrice,
            marketId = crypto.marketId,
            timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(crypto.timestamp), ZoneId.of(ARGENTINE_TIME_ZONE))
                .toIsoStringDate()
        )
    }
}
