package pt.zenit.helpers.binance.connector.client.impl.spot

import com.github.kittinunf.fuel.core.Client
import com.github.kittinunf.fuel.core.FuelManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import pt.zenit.helpers.binance.connector.client.impl.SpotClientImpl
import pt.zenit.helpers.binance.connector.client.utils.MockData


internal class MarketTest {

    private val client = mockk<Client>()

    @Test
    fun testPing() {
        val path = "https://localhost/api/v3/ping"

        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_OK
        every { client.executeRequest(any()).data } returns MockData.MOCK_RESPONSE.toByteArray()

        FuelManager.instance.client = client
        val client = SpotClientImpl(null, null, path)
        val result = client.createMarket().ping()
        assertEquals(MockData.MOCK_RESPONSE, result)
    }

    @Test
    fun testTime() {
        val path = "https://localhost/api/v3/ping"

        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_OK
        every { client.executeRequest(any()).data } returns MockData.MOCK_RESPONSE.toByteArray()

        FuelManager.instance.client = client
        val client = SpotClientImpl(null, null, path)
        val result = client.createMarket().time()
        assertEquals(MockData.MOCK_RESPONSE, result)
    }
}
