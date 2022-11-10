package pt.zenit.helpers.binance.connector.client.impl.spot

import com.github.kittinunf.fuel.core.Method
import pt.zenit.helpers.binance.connector.client.utils.RequestHandler


/**
 * <h2>Savings Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#savings-endpoints">Savings Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
class Savings(private val baseUrl: String, apiKey: String?, secretKey: String?, private val showLimitUsage: Boolean) {

    private val requestHandler: RequestHandler = RequestHandler(apiKey, secretKey)

    private val productPositionUrl = "/sapi/v1/lending/daily/token/position"

    /**
     * GET /sapi/v1/lending/daily/token/position
     * <br></br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     * where String is the name of the parameter and Object is the value of the parameter
     * <br></br><br></br>
     * asset -- optional/string <br></br>
     * recvWindow -- optional/long <br></br>
     * @return String
     * @see [
     * https://binance-docs.github.io/apidocs/spot/en/.get-flexible-product-position-user_data](https://binance-docs.github.io/apidocs/spot/en/.get-flexible-product-position-user_data)
     */
    fun flexibleProductPosition(parameters: LinkedHashMap<String?, Any>?): String {
        return requestHandler.sendSignedRequest(baseUrl, productPositionUrl, parameters, Method.GET, showLimitUsage)
    }
}
