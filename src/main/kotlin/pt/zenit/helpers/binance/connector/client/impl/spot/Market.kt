package pt.zenit.helpers.binance.connector.client.impl.spot

import com.github.kittinunf.fuel.core.Method
import pt.zenit.helpers.binance.connector.client.utils.RequestHandler

/**
 * <h2>Market Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#market-data-endpoints">Market Data Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
class Market(private val baseUrl: String, apiKey: String?, secretKey: String?, private val showLimitUsage: Boolean) {

    private val requestHandler: RequestHandler = RequestHandler(apiKey, secretKey)

    private val pingUrl = "/api/v3/ping"

    /**
     * Test connectivity to the Rest API.
     * <br></br><br></br>
     * GET /api/v3/ping
     * <br></br>
     * @return String
     * @see [
     * https://binance-docs.github.io/apidocs/spot/en/.test-connectivity](https://binance-docs.github.io/apidocs/spot/en/.test-connectivity)
     */
    fun ping(): String {
        return requestHandler.sendPublicRequest(baseUrl, pingUrl, null, Method.GET, showLimitUsage)
    }

    private val timeUrl = "/api/v3/time"

    /**
     * Test connectivity to the Rest API and get the current server time.
     * <br></br><br></br>
     * GET /api/v3/time
     * <br></br>
     * @return String
     * @see [
     * https://binance-docs.github.io/apidocs/spot/en/.check-server-time](https://binance-docs.github.io/apidocs/spot/en/.test-connectivity)
     */
    fun time(): String {
        return requestHandler.sendPublicRequest(baseUrl, timeUrl, null, Method.GET, showLimitUsage)
    }
}
