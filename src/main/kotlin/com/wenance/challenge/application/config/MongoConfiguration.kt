package com.wenance.challenge.application.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MongoConfiguration {

    @Bean
    fun mongoClient(): MongoClient {
        val connection = ConnectionString("mongodb://localhost:27017/test")
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connection)
            .build()

        return MongoClients.create(mongoClientSettings)
    }
}
