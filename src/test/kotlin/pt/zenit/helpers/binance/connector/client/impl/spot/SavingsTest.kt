package pt.zenit.helpers.binance.connector.client.impl.spot

import com.github.kittinunf.fuel.core.Client
import com.github.kittinunf.fuel.core.FuelManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pt.zenit.helpers.binance.connector.client.impl.SpotClientImpl
import pt.zenit.helpers.binance.connector.client.utils.MockData


internal class SavingsTest {

    private val client = mockk<Client>()

    @Test
    fun testFlexibleProductPosition() {
        val path = "https://localhost/sapi/v1/lending/daily/token/position"
        val parameters = LinkedHashMap<String?, Any>()

        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_OK
        every { client.executeRequest(any()).data } returns MockData.MOCK_RESPONSE.toByteArray()

        FuelManager.instance.client = client


        val client = SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, path)
        val result = client.createSavings().flexibleProductPosition(parameters)
        assertEquals(MockData.MOCK_RESPONSE, result)
    }
}
