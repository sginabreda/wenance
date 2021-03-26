package com.wenance.challenge.unit.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.domain.external.gateway.WenanceGateway
import com.wenance.challenge.domain.usecase.ListResultsUseCase
import com.wenance.challenge.util.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.time.ZonedDateTime

class ListResultsUseCaseTest {

    private lateinit var wenanceGateway: WenanceGateway
    private lateinit var listResultsUseCase: ListResultsUseCase

    private val fromDate = ZonedDateTime.now()
    private val toDate = ZonedDateTime.now()
    private val limit = 20
    private val offset = 0

    @BeforeEach
    fun setUp() {
        wenanceGateway = mock()
        listResultsUseCase = ListResultsUseCase(wenanceGateway)
    }

    @Test
    fun `test list results`() {
        // Given
        val cryptoCurrenciesList = listOf(
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
        `when`(wenanceGateway.listResults(fromDate, toDate, limit, offset)).thenReturn(cryptoCurrenciesList)

        val results = listResultsUseCase(fromDate, toDate, limit, offset)

        // Then
        assertEquals(cryptoCurrenciesList, results)
        verify(wenanceGateway).listResults(fromDate, toDate, limit, offset)
    }
}
