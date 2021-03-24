package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.external.gateway.WenanceGateway
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetAveragePriceUseCase(private val wenanceGateway: WenanceGateway) {

    operator fun invoke(fromDate: ZonedDateTime, toDate: ZonedDateTime): BigDecimal {
        return wenanceGateway.getAveragePrice(fromDate, toDate)
    }
}
