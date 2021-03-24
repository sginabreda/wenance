package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetAveragePriceUseCase(private val wenanceGateway: WenanceGateway) {

    operator fun invoke(
        cryptoCurrency: String,
        currency: String?,
        fromDate: ZonedDateTime,
        toDate: ZonedDateTime
    ): BigDecimal {
        val cryptoCurrencyValue = CryptoCurrencyCode.valueOf(cryptoCurrency)
        val currencyValue = currency?.let { CurrencyCode.valueOf(currency) }

        return wenanceGateway.listCryptoCurrencies(cryptoCurrencyValue, currencyValue, fromDate, toDate)
            .run {
                if (this.isEmpty())
                    return BigDecimal.ZERO
                else
                    calculateAveragePrice(this)
            }
    }

    private fun calculateAveragePrice(cryptoCurrencyList: List<CryptoCurrencyInfo>): BigDecimal {
        val sumOfSellingPrices = cryptoCurrencyList.map { it.sellingPrice }
            .reduce { sumOfSellingPrices, price -> sumOfSellingPrices + price }
        val amountOfRecords = cryptoCurrencyList.size.toBigDecimal()

        return sumOfSellingPrices / amountOfRecords
    }
}
