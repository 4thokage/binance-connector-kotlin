package pt.zenit.helpers.binance.connector.client.utils

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceConnectorException


internal class ParameterCheckerTest {
    private val mockObject = "mockObject"
    private val emptyString = ""


    @Test
    fun testCheckParameter() {
        ParameterChecker.checkParameter(MockData.MOCK_PARAMETERS, "key1", String::class.java)
        ParameterChecker.checkParameter(MockData.MOCK_PARAMETERS, "key2", Number::class.java)
    }

    @Test
    fun testCheckParameterNoKey() {
        assertThrows(BinanceConnectorException::class.java) {
            ParameterChecker.checkRequiredParameter(
                MockData.MOCK_PARAMETERS,
                "InvalidKey"
            )
        }
    }

    @Test
    fun testCheckParameterWrongType() {
        assertThrows(BinanceConnectorException::class.java) {
            ParameterChecker.checkParameterType(
                mockObject,
                Int::class.java, "mockObject"
            )
        }
    }

    @Test
    fun testCheckEmptyString() {
        assertThrows(BinanceConnectorException::class.java) {
            ParameterChecker.checkParameterType(
                emptyString,
                String::class.java, "mockObject"
            )
        }
    }

    @Test
    fun testCheckNull() {
        assertThrows(BinanceConnectorException::class.java) {
            ParameterChecker.checkParameterType(
                null,
                String::class.java, "mockObject"
            )
        }
    }

}
