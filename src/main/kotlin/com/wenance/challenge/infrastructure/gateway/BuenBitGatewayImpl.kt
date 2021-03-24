package com.wenance.challenge.infrastructure.gateway

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.infrastructure.client.buenbit.BuenBitClient
import com.wenance.challenge.infrastructure.mapper.toCryptoCurrencyInfo
import com.wenance.challenge.logger
import reactor.core.publisher.Mono

class BuenBitGatewayImpl(private val buenBitClient: BuenBitClient) : BuenBitGateway {

    private val log by logger()

    override fun listCryptoCurrencyInfo(): List<CryptoCurrencyInfo> {
        return buenBitClient.getBuenBitResponse()!!
            .let {
                log.info("Working on $it")
                listOf(
                    it.data.daiars.toCryptoCurrencyInfo(),
                    it.data.daiusd.toCryptoCurrencyInfo(),
                    it.data.btcars.toCryptoCurrencyInfo()
                )
            }
    }
}
