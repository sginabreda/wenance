package com.wenance.challenge.infrastructure.client.buenbit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BuenBitResponse(
    @JsonProperty(value = "object")
    val data: BuenBitData
) {
    data class BuenBitData(
        val daiars: BuenBitDetail,
        val daiusd: BuenBitDetail,
        val btcars: BuenBitDetail
    ) {
        data class BuenBitDetail(
            val currency: String,
            @JsonProperty(value = "bid_currency")
            val bidCurrency: String,
            @JsonProperty(value = "ask_currency")
            val askCurrency: String,
            @JsonProperty(value = "purchase_price")
            val purchasePrice: String,
            @JsonProperty(value = "selling_price")
            val sellingPrice: String,
            @JsonProperty(value = "market_identifier")
            val marketIdentifier: String
        )
    }
}
