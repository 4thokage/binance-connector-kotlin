package pt.zenit.helpers.binance.connector.client.utils

object MockData {
    private const val intValue2 = 2
    const val HTTP_STATUS_OK = 200
    const val HTTP_STATUS_CLIENT_ERROR = 400
    const val HTTP_STATUS_SERVER_ERROR = 502
    const val MOCK_RESPONSE = "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}"
    const val API_KEY = "apiKey"
    const val SECRET_KEY = "secretKey"
    val MOCK_PARAMETERS: LinkedHashMap<String?, Any> = object : LinkedHashMap<String?, Any>() {
        init {
            put("key1", "value1")
            put("key2", intValue2)
        }
    }
}
