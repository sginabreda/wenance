package com.wenance.challenge.infrastructure.repository.impl

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.infrastructure.repository.WenanceRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo

class WenanceRepositoryImpl(private val mongoTemplate: MongoTemplate) : WenanceRepository {

    override fun getBitcoinFromDate(timestamp: Long): List<CryptoCurrencyInfo> {
        val query = Query(Criteria.where("timestamp").isEqualTo(timestamp))
        return mongoTemplate.find(query, CryptoCurrencyInfo::class.java)
    }

    override fun listCryptoCurrencies(
        cryptoCurrency: CryptoCurrencyCode,
        currency: CurrencyCode?,
        fromDate: Long,
        toDate: Long
    ): List<CryptoCurrencyInfo> {
        val query = Query(
            Criteria.where("cryptoCurrency")
                .`is`(cryptoCurrency.name)
                .and("timestamp")
                .gte(fromDate)
                .and("timestamp")
                .lte(toDate)
                .also {
                    if (cryptoCurrency == CryptoCurrencyCode.DAI)
                        it.and("currency")
                            .`is`(currency!!.name)
                }
        )

        return mongoTemplate.find(query, CryptoCurrencyInfo::class.java)
    }

    override fun listResults(fromDate: Long?, toDate: Long?): List<CryptoCurrencyInfo> {
        TODO("Not yet implemented")
    }

}