package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import java.time.ZonedDateTime

class ListResultsUseCase(private val wenanceGateway: WenanceGateway) {

    operator fun invoke(fromDate: ZonedDateTime?, toDate: ZonedDateTime?, limit: Int?, offset: Int?): List<CryptoCurrencyInfo> {
        return wenanceGateway.listResults(fromDate, toDate, limit, offset)
    }
}
