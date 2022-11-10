package pt.zenit.helpers.binance.connector.client.utils

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceConnectorException


object RequestBuilder {

    private const val API_KEY_HEADER_NAME = "X-MBX-APIKEY"
    private const val CONTENT_TYPE_HEADER_NAME = "Content-Type"
    private const val CONTENT_TYPE_HEADER_VALUE = "application/x-www-form-urlencoded"

    fun buildPublicRequest(fullUrl: String, httpMethod: Method): Request {
        return try {
            val request: Request = when (httpMethod) {

                Method.GET -> Fuel.get(fullUrl)
                    .appendHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)

                else -> throw BinanceConnectorException("Invalid HTTP method: $httpMethod")
            }
            request
        } catch (e: IllegalArgumentException) {
            throw BinanceConnectorException("Invalid URL: " + e.message)
        }
    }


    fun buildApiKeyRequest(fullUrl: String, httpMethod: Method, apiKey: String): Request {
        return try {
            val request: Request = when (httpMethod) {
//                Method.POST -> Fuel.post(fullUrl)
//                    .appendHeader(API_KEY_HEADER_NAME, apiKey)

                Method.GET -> Fuel.get(fullUrl)
                    .appendHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
                    .appendHeader(API_KEY_HEADER_NAME, apiKey)

//                Method.PUT -> Fuel.put(fullUrl)
//                    .appendHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
//                    .appendHeader(API_KEY_HEADER_NAME, apiKey)
//
//                Method.DELETE -> Fuel.delete(fullUrl)
//                    .appendHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
//                    .appendHeader(API_KEY_HEADER_NAME, apiKey)

                else -> throw BinanceConnectorException("Invalid HTTP method: $httpMethod")
            }
            request
        } catch (e: IllegalArgumentException) {
            throw BinanceConnectorException("Invalid URL: " + e.message)
        }
    }


}
