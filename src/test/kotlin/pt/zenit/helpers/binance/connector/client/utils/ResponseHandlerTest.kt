package pt.zenit.helpers.binance.connector.client.utils

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Client
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceClientException
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceServerException
import pt.zenit.helpers.binance.connector.client.utils.ResponseHandler.handleResponse


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ResponseHandlerTest {

    private val responseMock = "VALID"
    private val client = mockk<Client>()
    private val request = Fuel.request(Method.GET, "https://localhost")


    @Test
    fun testHandleResponse() {

        every { client.executeRequest(any()).statusCode } returns 200
        every { client.executeRequest(any()).responseMessage } returns "OK"
        every { client.executeRequest(any()).data } returns responseMock.toByteArray()

        FuelManager.instance.client = client

        val result = handleResponse(request, false)
        assertEquals(responseMock, result)
    }


    @Test
    fun testWith400JSONErrorMsg() {

        val mockErrorMsg = "{\"code\":-1000, \"msg\":\"error\"}"
        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_CLIENT_ERROR
        every { client.executeRequest(any()).responseMessage } returns mockErrorMsg
        every { client.executeRequest(any()).data } returns mockErrorMsg.toByteArray()

        FuelManager.instance.client = client

        val thrown: BinanceClientException = assertThrows(BinanceClientException::class.java) {
            handleResponse(
                request,
                false
            )
        }
        assertTrue(thrown.message?.contains(mockErrorMsg) ?: false)
    }

    @Test
    fun testWith400ErrorMsg() {
        val mockErrorMsg = "Error Message"
        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_CLIENT_ERROR
        every { client.executeRequest(any()).responseMessage } returns mockErrorMsg
        every { client.executeRequest(any()).data } returns mockErrorMsg.toByteArray()

        FuelManager.instance.client = client

        val thrown: BinanceClientException = assertThrows(BinanceClientException::class.java) {
            handleResponse(
                request,
                false
            )
        }
        assertTrue(thrown.message?.contains(mockErrorMsg) ?: false)
    }

    @Test
    fun testWith500ErrorMsg() {
        val mockErrorMsg = "Error Message"
        every { client.executeRequest(any()).statusCode } returns MockData.HTTP_STATUS_SERVER_ERROR
        every { client.executeRequest(any()).responseMessage } returns mockErrorMsg
        every { client.executeRequest(any()).data } returns mockErrorMsg.toByteArray()

        FuelManager.instance.client = client
        val thrown: BinanceServerException = assertThrows(BinanceServerException::class.java) {
            handleResponse(
                request,
                false
            )
        }
        assertTrue(thrown.message!!.contains(mockErrorMsg))
    }

}
