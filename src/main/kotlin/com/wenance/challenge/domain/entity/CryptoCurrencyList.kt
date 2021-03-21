package com.wenance.challenge.domain.entity

import java.time.ZonedDateTime

data class CryptoCurrencyList(
    val cryptoCurrencies: List<CryptoCurrencyInfo>,
    val date: ZonedDateTime
)
