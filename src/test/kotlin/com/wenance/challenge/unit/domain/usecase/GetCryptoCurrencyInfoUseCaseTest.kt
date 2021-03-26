package com.wenance.challenge.unit.domain.usecase

import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.domain.enums.CryptoCurrencyCode
import com.wenance.challenge.domain.enums.CurrencyCode
import com.wenance.challenge.domain.external.gateway.BuenBitGateway
import com.wenance.challenge.domain.usecase.GetCryptoCurrencyInfoUseCase
import com.wenance.challenge.infrastructure.repository.BuenBitRepository
import com.wenance.challenge.util.mock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.time.ZonedDateTime

class GetCryptoCurrencyInfoUseCaseTest {

    private lateinit var buenBitGateway: BuenBitGateway
    private lateinit var buenBitRepository: BuenBitRepository
    private lateinit var getCryptoCurrencyInfoUseCase: GetCryptoCurrencyInfoUseCase

    @BeforeEach
    fun setUp() {
        buenBitGateway = mock()
        buenBitRepository = mock()
        getCryptoCurrencyInfoUseCase = GetCryptoCurrencyInfoUseCase(buenBitGateway, buenBitRepository)
    }

    @Test
    fun `test get crypto currency`() {
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
        `when`(buenBitGateway.listCryptoCurrencyInfo()).thenReturn(cryptoCurrenciesList)
        getCryptoCurrencyInfoUseCase()

        // Then
        verify(buenBitGateway).listCryptoCurrencyInfo()
        verify(buenBitRepository).insertRecord(cryptoCurrenciesList.first())
    }
}
