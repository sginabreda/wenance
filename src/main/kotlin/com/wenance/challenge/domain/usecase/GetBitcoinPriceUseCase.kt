package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.exception.RequestException
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetBitcoinPriceUseCase(private val wenanceGateway: WenanceGateway) {

    operator fun invoke(fromDate: ZonedDateTime): BigDecimal {
        return wenanceGateway.getBitcoinPrice(fromDate)
            .let {
                if (it.isEmpty())
                    throw RequestException("No bitcoin was found for given timestamp", "not.found", HttpStatus.NOT_FOUND.value())
                it.first().sellingPrice
            }
    }
}
