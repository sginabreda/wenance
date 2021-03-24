package com.wenance.challenge.infrastructure.repository

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import org.springframework.data.mongodb.repository.MongoRepository

interface BuenBitRepository : MongoRepository<CryptoCurrencyInfo, String>
