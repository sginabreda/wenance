package com.wenance.challenge.application.controller

import com.wenance.challenge.delivery.WenanceController
import com.wenance.challenge.delivery.dto.response.BitcoinPriceDto
import com.wenance.challenge.delivery.dto.response.CryptoCurrencyAveragePriceDto
import com.wenance.challenge.delivery.dto.response.CryptoCurrencyListDto
import com.wenance.challenge.delivery.dto.validator.AveragePriceValidator.validate
import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import com.wenance.challenge.util.toDto
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

    @GetMapping("average-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getAveragePrice(
        @RequestParam cryptoCurrency: String,
        @RequestParam currency: String?,
        @RequestParam fromDate: ZonedDateTime,
        @RequestParam toDate: ZonedDateTime
    ): CryptoCurrencyAveragePriceDto {
        validate(cryptoCurrency, currency)
        return CryptoCurrencyAveragePriceDto(getAveragePriceUseCase(cryptoCurrency, currency, fromDate, toDate))
    }

    @GetMapping("results")
    @ResponseStatus(HttpStatus.OK)
    override fun listResults(
        @RequestParam fromDate: ZonedDateTime?,
        @RequestParam toDate: ZonedDateTime?,
        @RequestParam limit: Int?,
        @RequestParam offset: Int?
    ): CryptoCurrencyListDto {
        return listResultsUseCase(fromDate, toDate, limit, offset).toDto()
    }
}
