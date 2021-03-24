package com.wenance.challenge.domain.usecase

import com.wenance.challenge.domain.external.gateway.WenanceGateway
import java.time.ZonedDateTime

class ListResultsUseCase(private val wenanceGateway: WenanceGateway) {

    operator fun invoke(fromDate: ZonedDateTime?, toDate: ZonedDateTime?) {

    }
}
