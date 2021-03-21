package com.wenance.challenge.application.config

import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WenanceConfiguration {

    @Bean
    fun getBitcoinPrice(): GetBitcoinPriceUseCase {
        return GetBitcoinPriceUseCase()
    }

    @Bean
    fun getAveragePrice(): GetAveragePriceUseCase {
        return GetAveragePriceUseCase()
    }

    @Bean
    fun listResults(): ListResultsUseCase {
        return ListResultsUseCase()
    }
}