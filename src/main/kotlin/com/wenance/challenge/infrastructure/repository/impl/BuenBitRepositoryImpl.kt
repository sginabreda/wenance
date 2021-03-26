package com.wenance.challenge.infrastructure.repository.impl

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
import org.springframework.data.mongodb.core.MongoTemplate

class BuenBitRepositoryImpl(private val mongoTemplate: MongoTemplate): BuenBitRepository {

    override fun insertRecord(detail: CryptoCurrencyInfo): CryptoCurrencyInfo {
        return mongoTemplate.insert(detail)
    }
}
