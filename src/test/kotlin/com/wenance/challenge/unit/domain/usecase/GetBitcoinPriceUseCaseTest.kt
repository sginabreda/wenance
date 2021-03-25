package com.wenance.challenge.unit.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.domain.exception.RequestException
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.domain.usecase.GetBitcoinPriceUseCase
import com.wenance.challenge.util.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetBitcoinPriceUseCaseTest {

    private lateinit var wenanceGateway: WenanceGateway
    private lateinit var getBitcoinPriceUseCase: GetBitcoinPriceUseCase

    private lateinit var cryptoCurrencyInfo: CryptoCurrencyInfo
    private val fromDate = ZonedDateTime.now()

    @BeforeEach
    fun setUp() {
        wenanceGateway = mock()
        getBitcoinPriceUseCase = GetBitcoinPriceUseCase(wenanceGateway)
    }

    @Test
    fun `test get bitcoin price`() {
        // Given
        val expectedPrice = BigDecimal(8500000)
        cryptoCurrencyInfo = CryptoCurrencyInfo(
            cryptoCurrency = CryptoCurrencyCode.BITCOIN,
            currency = CurrencyCode.ARS,
            purchasePrice = BigDecimal(8339000),
            sellingPrice = expectedPrice,
            marketId = "btcars",
            timestamp = ZonedDateTime.now().toEpochSecond()
        )

        // When
        `when`(wenanceGateway.getBitcoinFromDate(fromDate)).thenReturn(cryptoCurrencyInfo)

        val bitcoinPrice = getBitcoinPriceUseCase(fromDate)

        // Then
        assertEquals(expectedPrice, bitcoinPrice)
        verify(wenanceGateway).getBitcoinFromDate(fromDate)
    }

    @Test
    fun `test get bitcoin price, throws exception`() {
        // When
        `when`(wenanceGateway.getBitcoinFromDate(fromDate)).thenReturn(null)

        // Then
        assertThrows(RequestException::class.java) { getBitcoinPriceUseCase(fromDate) }
    }
}
