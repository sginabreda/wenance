package com.wenance.challenge.util.mother

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import java.math.BigDecimal
import java.time.ZonedDateTime

object CryptoCurrencyInfoMother {

    fun aCryptoCurrency(
        cryptoCurrency: CryptoCurrencyCode? = CryptoCurrencyCode.BITCOIN,
        currency: CurrencyCode? = CurrencyCode.ARS,
        purchasePrice: BigDecimal? = BigDecimal(7674500),
        sellingPrice: BigDecimal? = BigDecimal(7904700),
        marketId: String? = "btcars",
        timestamp: Long? = ZonedDateTime.now().toEpochSecond()
    ): CryptoCurrencyInfo {
        return CryptoCurrencyInfo(
            cryptoCurrency = cryptoCurrency!!,
            currency = currency!!,
            purchasePrice = purchasePrice!!,
            sellingPrice = sellingPrice!!,
            marketId = marketId!!,
            timestamp = timestamp!!
        )
    }
}
