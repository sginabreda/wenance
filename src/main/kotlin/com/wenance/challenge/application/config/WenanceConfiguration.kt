package com.wenance.challenge.application.config

import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.domain.usecase.GetCryptoCurrencyInfoUseCase
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import com.wenance.challenge.infrastructure.gateway.WenanceGatewayImpl
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
import com.wenance.challenge.infrastructure.repository.WenanceRepository
import com.wenance.challenge.infrastructure.repository.impl.BuenBitRepositoryImpl
import com.wenance.challenge.infrastructure.repository.impl.WenanceRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class WenanceConfiguration {

    @Bean
    fun getBitcoinPrice(wenanceGateway: WenanceGateway): GetBitcoinPriceUseCase {
        return GetBitcoinPriceUseCase(wenanceGateway)
    }

    @Bean
    fun getAveragePrice(wenanceGateway: WenanceGateway): GetAveragePriceUseCase {
        return GetAveragePriceUseCase(wenanceGateway)
    }

    @Bean
    fun listResults(wenanceGateway: WenanceGateway): ListResultsUseCase {
        return ListResultsUseCase(wenanceGateway)
    }


    @Bean
    fun getCryptoCurrencyInfo(
        buenBitGateway: BuenBitGateway,
        cryptoCurrencyRepository: BuenBitRepository
    ): GetCryptoCurrencyInfoUseCase {
        return GetCryptoCurrencyInfoUseCase(buenBitGateway, cryptoCurrencyRepository)
    }

    @Bean
    fun buenBitRepository(mongoTemplate: MongoTemplate): BuenBitRepository {
        return BuenBitRepositoryImpl(mongoTemplate)
    }

    @Bean
    fun wenanceGateway(wenanceRepository: WenanceRepository): WenanceGateway {
        return WenanceGatewayImpl(wenanceRepository)
    }

    @Bean
    fun wenanceRepository(mongoTemplate: MongoTemplate): WenanceRepository {
        return WenanceRepositoryImpl(mongoTemplate)
    }
}
