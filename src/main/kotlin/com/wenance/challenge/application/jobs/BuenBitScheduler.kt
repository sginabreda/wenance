package com.wenance.challenge.application.jobs

import com.wenance.challenge.domain.usecase.GetCryptoCurrencyInfoUseCase
import com.wenance.challenge.logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BuenBitScheduler(private val getCryptoCurrencyInfoUseCase: GetCryptoCurrencyInfoUseCase) {

    private val log by logger()

    @Scheduled(fixedRate = 10_000)
    fun getCryptoCurrencyInfoSchedule() {
        log.info("Starting job...")
        getCryptoCurrencyInfoUseCase().subscribe()
    }
}
