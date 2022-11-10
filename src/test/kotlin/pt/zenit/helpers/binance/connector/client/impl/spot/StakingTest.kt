package pt.zenit.helpers.binance.connector.client.impl.spot

import com.github.kittinunf.fuel.core.Client
import com.github.kittinunf.fuel.core.FuelManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceConnectorException
import pt.zenit.helpers.binance.connector.client.impl.SpotClientImpl
import pt.zenit.helpers.binance.connector.client.utils.MockData


internal class StakingTest {
    private val client = mockk<Client>()

    @Test
    fun testProductPosition() {
        val path = "https://localhost/sapi/v1/staking/position"
        val parameters = LinkedHashMap<String?, Any>()
        parameters["product"] = "STAKING"

        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_OK
        every { client.executeRequest(any()).data } returns MockData.MOCK_RESPONSE.toByteArray()

        FuelManager.instance.client = client


        val client = SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, path)
        val result = client.createStaking().getPosition(parameters)
        assertEquals(MockData.MOCK_RESPONSE, result)
    }


    @Test
    fun testProductPositionWithoutParameter() {
        val path = "https://localhost/sapi/v1/staking/position"
        val parameters = LinkedHashMap<String?, Any>()

        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_OK
        every { client.executeRequest(any()).data } returns MockData.MOCK_RESPONSE.toByteArray()

        FuelManager.instance.client = client

        val client = SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, path)
        val thrown: BinanceConnectorException = Assertions.assertThrows(BinanceConnectorException::class.java) {
            client.createStaking().getPosition(parameters)
        }
    }
}
