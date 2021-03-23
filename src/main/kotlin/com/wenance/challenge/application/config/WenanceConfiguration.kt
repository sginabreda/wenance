package com.wenance.challenge.application.config

import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.domain.usecase.GetCryptoCurrencyInfo
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import com.wenance.challenge.infrastructure.client.buenbit.BuenBitClient
import com.wenance.challenge.infrastructure.gateway.BuenBitGatewayImpl
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
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

    @Bean
    fun getCryptoCurrencyInfo(
        buenBitGateway: BuenBitGateway,
        cryptoCurrencyRepository: BuenBitRepository
    ): GetCryptoCurrencyInfo {
        return GetCryptoCurrencyInfo(buenBitGateway, cryptoCurrencyRepository)
    }

    @Bean
    fun buenBitGateway(buenBitClient: BuenBitClient): BuenBitGateway {
        return BuenBitGatewayImpl(buenBitClient)
    }
}
