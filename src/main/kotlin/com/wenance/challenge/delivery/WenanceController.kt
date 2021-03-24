package com.wenance.challenge.delivery

import com.wenance.challenge.delivery.dto.response.BitcoinAveragePriceDto
import com.wenance.challenge.delivery.dto.response.BitcoinPriceDto
import com.wenance.challenge.util.Constants.DATETIME_PATTERN
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

interface WenanceController {
    fun getBitcoinPrice(
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        fromDate: ZonedDateTime
    ): BitcoinPriceDto

    fun getAveragePrice(
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        fromDate: ZonedDateTime,
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        toDate: ZonedDateTime
    ): BitcoinAveragePriceDto

    fun listResults(
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        fromDate: ZonedDateTime?,
        @DateTimeFormat(pattern = DATETIME_PATTERN)
        toDate: ZonedDateTime?
    )
}
