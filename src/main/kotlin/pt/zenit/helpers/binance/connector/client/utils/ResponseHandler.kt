package pt.zenit.helpers.binance.connector.client.utils

import com.beust.klaxon.JsonParsingException
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.isClientError
import com.github.kittinunf.fuel.core.isServerError
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceClientException
import pt.zenit.helpers.binance.connector.client.exceptions.BinanceServerException


object ResponseHandler {

    fun handleResponse(req: Request, showLimitUsage: Boolean): String {

        val response = FuelManager.instance.client.executeRequest(req)

        if (response.isServerError) {
            throw BinanceServerException(response.responseMessage, response.statusCode)
        } else if (response.isClientError) {
            throw handleErrorResponse(response.responseMessage, response.statusCode)
        }

        return String(response.data)
    }


    private fun handleErrorResponse(responseBody: String, responseCode: Number): Throwable {
        return try {
            val errorMsg: String = Klaxon().toJsonString(responseBody)
            val errorCode: Number = 400
            BinanceClientException(responseBody, errorMsg, responseCode, errorCode)
        } catch (e: JsonParsingException) {
            throw BinanceClientException(responseBody, responseCode)
        }
    }
}
