package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.exception.RequestException
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetBitcoinPriceUseCase(private val wenanceGateway: WenanceGateway) {

    operator fun invoke(fromDate: ZonedDateTime): BigDecimal {
        return wenanceGateway.getBitcoinPrice(fromDate)
            .map { it.sellingPrice }
            .blockFirst() ?: throw RequestException("Bitcoin with given timestamp not found", "not.found", HttpStatus.NOT_FOUND.value())
    }
}
