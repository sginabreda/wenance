package com.wenance.challenge.delivery

import com.wenance.challenge.delivery.dto.response.CryptoCurrencyAveragePriceDto
import com.wenance.challenge.delivery.dto.response.BitcoinPriceDto
import com.wenance.challenge.delivery.dto.response.CryptoCurrencyListDto
import com.wenance.challenge.util.Constants.DATETIME_PATTERN
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern

interface WenanceController {
    fun getBitcoinPrice(
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        fromDate: ZonedDateTime
    ): BitcoinPriceDto

    fun getAveragePrice(
        @Pattern(regexp = "(DAI|BITCOIN)")
        cryptoCurrency: String,
        @Pattern(regexp = "(ARS|USD)")
        currency: String?,
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        fromDate: ZonedDateTime,
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        toDate: ZonedDateTime
    ): CryptoCurrencyAveragePriceDto

    fun listResults(
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        fromDate: ZonedDateTime?,
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        toDate: ZonedDateTime?,
        @Min(0)
        @Max(50)
        limit: Int?,
        @Min(0)
        @Max(50)
        offset: Int?,
    ): CryptoCurrencyListDto
}
