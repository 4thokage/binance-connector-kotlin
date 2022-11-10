package pt.zenit.helpers.binance.connector.client.impl.spot

import com.github.kittinunf.fuel.core.Method
import pt.zenit.helpers.binance.connector.client.utils.ParameterChecker
import pt.zenit.helpers.binance.connector.client.utils.RequestHandler

/**
 * <h2>Staking Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#staking-endpoints">Staking Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
class Staking(private val baseUrl: String, apiKey: String?, secretKey: String?, private val showLimitUsage: Boolean) {

    private val requestHandler: RequestHandler = RequestHandler(apiKey, secretKey)

    private val productPositionUrl = "/sapi/v1/staking/position"

    /**
     * GET /sapi/v1/staking/position
     * <br></br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     * where String is the name of the parameter and Object is the value of the parameter
     * <br></br><br></br>
     * product -- mandatory/enum -- "STAKING" for Locked Staking, "F_DEFI" for flexible DeFi Staking, "L_DEFI" for locked DeFi Staking <br></br>
     * productId -- mandatory/STRING <br></br>
     * asset -- optional/string <br></br>
     * current -- optional/long -- Currently querying page. Start from 1. Default:1 <br></br>
     * size -- optional/long -- Default: 50, Max: 100 <br></br>
     * recvWindow -- optional/long <br></br>
     * @return String
     * @see [
     * https://binance-docs.github.io/apidocs/spot/en/.get-staking-product-position-user_data](https://binance-docs.github.io/apidocs/spot/en/.get-staking-product-position-user_data)
     */
    fun getPosition(parameters: LinkedHashMap<String?, Any>): String {
        ParameterChecker.checkParameter(parameters, "product", String::class.java)
        return requestHandler.sendSignedRequest(baseUrl, productPositionUrl, parameters, Method.GET, showLimitUsage)
    }
}
