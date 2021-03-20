package com.wenance.challenge

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.ParameterizedTypeReference
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ChallengeApplication

fun main(args: Array<String>) {
	runApplication<ChallengeApplication>(*args)
}

fun <R : Any> R.logger(): Lazy<Logger> {
	return lazy { LoggerFactory.getLogger(this.javaClass) }
}

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}