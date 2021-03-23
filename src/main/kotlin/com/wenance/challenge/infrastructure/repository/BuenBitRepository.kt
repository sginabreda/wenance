package com.wenance.challenge.infrastructure.repository

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface BuenBitRepository : ReactiveMongoRepository<CryptoCurrencyInfo, String>
