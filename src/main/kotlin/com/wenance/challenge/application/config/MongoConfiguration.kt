package com.wenance.challenge.application.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

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

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), "test")
    }
}
