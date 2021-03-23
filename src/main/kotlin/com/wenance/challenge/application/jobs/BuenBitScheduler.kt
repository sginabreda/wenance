package com.wenance.challenge.application.jobs

import com.wenance.challenge.domain.usecase.GetCryptoCurrencyInfo
import com.wenance.challenge.logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BuenBitScheduler(private val getCryptoCurrencyInfo: GetCryptoCurrencyInfo) {

    private val log by logger()

    @Scheduled(fixedRate = 10_000)
    fun getCryptoCurrencyInfoSchedule() {
        log.info("Starting job...")
        getCryptoCurrencyInfo().subscribe()
    }
}
