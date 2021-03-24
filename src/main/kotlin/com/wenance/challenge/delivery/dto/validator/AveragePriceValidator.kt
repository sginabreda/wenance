package com.wenance.challenge.delivery.dto.validator

import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.exception.RequestException
import org.springframework.http.HttpStatus

object AveragePriceValidator {
    fun validate(cryptoCurrency: String, currency: String?) {
        if (cryptoCurrency == CryptoCurrencyCode.DAI.name && currency.isNullOrBlank())
            throw RequestException(
                "DAI cryptoCurrency must come with a currency",
                "bad.request",
                HttpStatus.BAD_REQUEST.value()
            )
    }
}
