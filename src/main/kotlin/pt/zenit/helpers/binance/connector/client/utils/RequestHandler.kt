package pt.zenit.helpers.binance.connector.client.utils

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import pt.zenit.helpers.binance.connector.client.enums.RequestType
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceConnectorException


class RequestHandler(private val apiKey: String?, private val secretKey: String?) {

    /**
     * Build request based on request type and send the requests to server.
     * @param baseUrl
     * @param urlPath
     * @param signature
     * @param parameters
     * @param httpMethod
     * @param requestType
     * @return String - response from server
     */
    private fun sendApiRequest(
        baseUrl: String, urlPath: String, signature: String?, parameters: LinkedHashMap<String?, Any>?,
        httpMethod: Method, requestType: RequestType, showLimitUsage: Boolean
    ): String {
        val fullUrl: String = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters, signature)
        val request: Request = when (requestType) {
            RequestType.PUBLIC -> RequestBuilder.buildPublicRequest(fullUrl, httpMethod)
            RequestType.WITH_API_KEY, RequestType.SIGNED -> RequestBuilder.buildApiKeyRequest(
                fullUrl,
                httpMethod,
                apiKey!!
            )
        }
        return ResponseHandler.handleResponse(request, showLimitUsage)
    }

    fun sendPublicRequest(
        baseUrl: String?, urlPath: String?, parameters: LinkedHashMap<String?, Any>?,
        httpMethod: Method, showLimitUsage: Boolean
    ): String {
        return sendApiRequest(
            baseUrl!!,
            urlPath!!, null, parameters, httpMethod, RequestType.PUBLIC, showLimitUsage
        )
    }


    fun sendSignedRequest(
        baseUrl: String,
        suffixUrl: String,
        parameters: LinkedHashMap<String?, Any>?,
        httpMethod: Method,
        showLimitUsage: Boolean
    ): String {
        if (null == secretKey || secretKey.isEmpty() || null == apiKey || apiKey.isEmpty()) {
            throw BinanceConnectorException("[RequestHandler] Secret key/API key cannot be null or empty!")
        }
        parameters!!["timestamp"] = UrlBuilder.buildTimestamp()
        val queryString: String = UrlBuilder.joinQueryParameters(parameters)
        val signature = SignatureGenerator.getSignature(queryString, secretKey)
        return sendApiRequest(baseUrl, suffixUrl, signature, parameters, httpMethod, RequestType.SIGNED, showLimitUsage)
    }

}
