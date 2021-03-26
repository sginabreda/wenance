package com.wenance.challenge.functional

import com.wenance.challenge.delivery.dto.response.BitcoinPriceDto
import com.wenance.challenge.domain.entity.CryptoCurrencyInfo
import com.wenance.challenge.util.Constants.ARGENTINE_TIME_ZONE
import com.wenance.challenge.util.mother.CryptoCurrencyInfoMother.aCryptoCurrency
import com.wenance.challenge.util.toIsoStringDate
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockserver.client.server.MockServerClient
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.io.File
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class WenanceResourceFunctionalTest {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    private lateinit var mockServer: MockServerClient
    private lateinit var buenBitResponse: String
    private lateinit var controller: String
    private lateinit var entity: HttpEntity<*>

    private final val buenBitResponseJson = "src/test/resources/json/buenbit_response.json"
    private final val buenBitPath = "/api/market/tickers"
    private final val timestamp = 1616613585L

    @BeforeEach
    fun setUp() {
        mockServer = ClientAndServer.startClientAndServer(8081)
        buenBitResponse = File(buenBitResponseJson).readText()
        controller = testRestTemplate.rootUri
        entity = HttpEntity(null, HttpHeaders())

        mockServer.`when`(
            HttpRequest.request()
                .withMethod(HttpMethod.GET.name)
                .withPath(buenBitPath)
        )
            .respond(
                HttpResponse.response()
                    .withHeader("Content-Type", "application/json")
                    .withStatusCode(HttpStatus.OK.value())
                    .withBody(buenBitResponse)
            )

        mongoTemplate.insert(aCryptoCurrency(timestamp = timestamp))
    }

    @AfterEach
    fun tearDownAfterEach() {
        mongoTemplate.remove(CryptoCurrencyInfo::class.java)
        mockServer.reset()
    }

    @AfterAll
    fun afterAll() {
        mockServer.stop()
    }

    @Test
    fun `test get bitcoin price`() {
        // Given
        val bitcoinPriceUrl = "$controller/bitcoin-price?fromDate={fromDate}"
        val fromDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of(ARGENTINE_TIME_ZONE))
            .toIsoStringDate()
        val parameters = mapOf<String, Any>(
            "fromDate" to fromDate
        )

        // When
        val bitcoinPrice = testRestTemplate.exchange(
            bitcoinPriceUrl,
            HttpMethod.GET,
            entity,
            BitcoinPriceDto::class.java,
            parameters
        )

        // Then
        assertNotNull(bitcoinPrice)
        assertEquals(BigDecimal(7904700), bitcoinPrice.body?.bitcoinPrice)
    }
}
