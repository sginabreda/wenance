package com.wenance.challenge.application.controller

import com.wenance.challenge.delivery.WenanceController
import com.wenance.challenge.delivery.dto.response.BitcoinAveragePriceDto
import com.wenance.challenge.delivery.dto.response.BitcoinPriceDto
import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@Validated
@RestController
class WenanceResource(
    private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
    private val getAveragePriceUseCase: GetAveragePriceUseCase,
    private val listResultsUseCase: ListResultsUseCase
) : WenanceController {

    @GetMapping("bitcoin-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getBitcoinPrice(@RequestParam fromDate: ZonedDateTime): BitcoinPriceDto {
        return BitcoinPriceDto(getBitcoinPriceUseCase(fromDate))
    }

    @GetMapping("bitcoin-avg-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getAveragePrice(@RequestParam fromDate: ZonedDateTime, @RequestParam toDate: ZonedDateTime): BitcoinAveragePriceDto {
        return BitcoinAveragePriceDto(getAveragePriceUseCase(fromDate, toDate))
    }

    @GetMapping("results")
    @ResponseStatus(HttpStatus.OK)
    override fun listResults(@RequestParam fromDate: ZonedDateTime?, @RequestParam toDate: ZonedDateTime?) {
        return listResultsUseCase(fromDate, toDate)
    }
}
