package com.wenance.challenge.application.controller

import com.wenance.challenge.delivery.WenanceController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
class WenanceResource : WenanceController {

    @GetMapping("bitcoin-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getBitcoinPrice(from: ZonedDateTime) {
        TODO("Not yet implemented")
    }

    @GetMapping("bitcoin-avg-price")
    @ResponseStatus(HttpStatus.OK)
    override fun getAveragePrice(fromDate: ZonedDateTime, toDate: ZonedDateTime) {
        TODO("Not yet implemented")
    }

    @GetMapping("results")
    @ResponseStatus(HttpStatus.OK)
    override fun listResults(from: ZonedDateTime?, to: ZonedDateTime?) {
        TODO("Not yet implemented")
    }
}
