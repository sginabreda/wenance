package com.wenance.challenge.infrastructure.client.buenbit.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BuenBitResponse(
    @JsonProperty(value = "object")
    val data: String,
    val errors: List<BuenBitError>
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
            val purchasePrice: BigDecimal,
            @JsonProperty(value = "selling_price")
            val sellingPrice: BigDecimal,
            @JsonProperty(value = "market_identifier")
            val marketIdentifier: String
        )
    }

    data class BuenBitError(
        val message: String
    )
}
