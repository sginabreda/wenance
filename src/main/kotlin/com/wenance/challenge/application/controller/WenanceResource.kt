package com.wenance.challenge.application.controller

import com.wenance.challenge.delivery.WenanceController
import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.ZonedDateTime

@RestController
class WenanceResource(
    private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
    private val getAveragePriceUseCase: GetAveragePriceUseCase,
    private val listResultsUseCase: ListResultsUseCase
) : WenanceController {

    @GetMapping("bitcoin-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getBitcoinPrice(@RequestParam fromDate: ZonedDateTime): BigDecimal {
        return getBitcoinPriceUseCase(fromDate)
    }

    @GetMapping("bitcoin-avg-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getAveragePrice(@RequestParam fromDate: ZonedDateTime, @RequestParam toDate: ZonedDateTime): BigDecimal {
        return getAveragePriceUseCase(fromDate, toDate)
    }

    @GetMapping("results")
    @ResponseStatus(HttpStatus.OK)
    override fun listResults(@RequestParam from: ZonedDateTime?, @RequestParam to: ZonedDateTime?) {
        return listResultsUseCase(from, to)
    }
}
