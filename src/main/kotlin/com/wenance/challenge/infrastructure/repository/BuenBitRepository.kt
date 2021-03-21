package com.wenance.challenge.infrastructure.repository

import com.wenance.challenge.infrastructure.client.buenbit.dto.BuenBitResponse
import org.springframework.data.mongodb.repository.MongoRepository

interface BuenBitRepository : MongoRepository<BuenBitResponse, String>
