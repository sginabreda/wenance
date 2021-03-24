package com.wenance.challenge.infrastructure.repository.impl

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.infrastructure.repository.WenanceRepository
import com.wenance.challenge.util.ifNotNull
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class WenanceRepositoryImpl(private val mongoTemplate: MongoTemplate) : WenanceRepository {

    override fun getBitcoinFromDate(timestamp: Long): List<CryptoCurrencyInfo> {
        val query = Query(
            Criteria.where("timestamp")
                .`is`(timestamp)
                .and("cryptoCurrency")
                .`is`("BITCOIN")
        )
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
        val query = if (fromDate == null && toDate == null)
            Query()
        else
            Query(Criteria.where("timestamp")
                .also { fromDate.ifNotNull { fd -> it.gte(fd) } }
                .also { toDate.ifNotNull { td -> it.lte(td) } }
            )

        return mongoTemplate.find(query, CryptoCurrencyInfo::class.java)
    }

}