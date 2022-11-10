package pt.zenit.helpers.binance.connector.client.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SignatureGeneratorTest {

    @Test
    fun testFixedSignature() {
        val joinedQuery = "91c330ed5f85f624c95d8a77e93edf0e22a71396ed06b09b405d25733c46f117"
        assertEquals(joinedQuery, SignatureGenerator.getSignature("MyData", "MyKey"))
    }
}
