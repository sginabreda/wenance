package com.wenance.challenge.unit.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.domain.usecase.GetAveragePriceUseCase
import com.wenance.challenge.util.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetAveragePriceUseCaseTest {

    private lateinit var wenanceGateway: WenanceGateway
    private lateinit var getAveragePriceUseCase: GetAveragePriceUseCase

    private lateinit var cryptoCurrenciesList: List<CryptoCurrencyInfo>
    private val cryptoCurrency = "BITCOIN"
    private val fromDate = ZonedDateTime.now()
    private val toDate = ZonedDateTime.now()

    @BeforeEach
    fun setUp() {
        wenanceGateway = mock()
        getAveragePriceUseCase = GetAveragePriceUseCase(wenanceGateway)
    }

    @Test
    fun `test get average price for bitcoin`() {
        // Given
        cryptoCurrenciesList = listOf(
            CryptoCurrencyInfo(
                cryptoCurrency = CryptoCurrencyCode.BITCOIN,
                currency = CurrencyCode.ARS,
                purchasePrice = BigDecimal(8339000),
                sellingPrice = BigDecimal(8500000),
                marketId = "btcars",
                timestamp = ZonedDateTime.now().toEpochSecond()
            )
        )

        // When
        `when`(wenanceGateway.listCryptoCurrencies(CryptoCurrencyCode.BITCOIN, null, fromDate, toDate))
            .thenReturn(cryptoCurrenciesList)

        val averagePrice = getAveragePriceUseCase(cryptoCurrency, null, fromDate, toDate)

        // Then
        assertEquals(BigDecimal(8500000), averagePrice)
        verify(wenanceGateway).listCryptoCurrencies(CryptoCurrencyCode.BITCOIN, null, fromDate, toDate)
    }

    @Test
    fun `test get average price given two records in list`() {
        // Given
        cryptoCurrenciesList = listOf(
            CryptoCurrencyInfo(
                cryptoCurrency = CryptoCurrencyCode.BITCOIN,
                currency = CurrencyCode.ARS,
                purchasePrice = BigDecimal(8339000),
                sellingPrice = BigDecimal(8500000),
                marketId = "btcars",
                timestamp = ZonedDateTime.now().toEpochSecond()
            ),
            CryptoCurrencyInfo(
                cryptoCurrency = CryptoCurrencyCode.BITCOIN,
                currency = CurrencyCode.ARS,
                purchasePrice = BigDecimal(8339000),
                sellingPrice = BigDecimal(8600000),
                marketId = "btcars",
                timestamp = ZonedDateTime.now().toEpochSecond()
            )
        )
        val averagePriceExpected = cryptoCurrenciesList.sumOf { it.sellingPrice } / cryptoCurrenciesList.size.toBigDecimal()

        // When
        `when`(wenanceGateway.listCryptoCurrencies(CryptoCurrencyCode.BITCOIN, null, fromDate, toDate))
            .thenReturn(cryptoCurrenciesList)

        val averagePrice = getAveragePriceUseCase(cryptoCurrency, null, fromDate, toDate)

        // Then
        assertEquals(averagePriceExpected, averagePrice)
    }

    @Test
    fun `test get average price, returns zero`() {
        // Given
        cryptoCurrenciesList = emptyList()
        val averagePriceExpected = BigDecimal.ZERO

        // When
        `when`(wenanceGateway.listCryptoCurrencies(CryptoCurrencyCode.BITCOIN, null, fromDate, toDate))
            .thenReturn(cryptoCurrenciesList)

        val averagePrice = getAveragePriceUseCase(cryptoCurrency, null, fromDate, toDate)

        // Then
        assertEquals(averagePriceExpected, averagePrice)
    }
}
