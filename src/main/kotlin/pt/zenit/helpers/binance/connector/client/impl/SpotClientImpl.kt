package pt.zenit.helpers.binance.connector.client.impl

import pt.zenit.helpers.binance.connector.client.SpotClient
import pt.zenit.helpers.binance.connector.client.enums.DefaultUrls
import pt.zenit.helpers.binance.connector.client.impl.spot.Market
import pt.zenit.helpers.binance.connector.client.impl.spot.Savings
import pt.zenit.helpers.binance.connector.client.impl.spot.Staking

class SpotClientImpl(
    private val apiKey: String?,
    private val secretKey: String?,
    private val baseUrl: String = DefaultUrls.PROD_URL
) : SpotClient {

    private var showLimitUsage = false

    override fun createMarket() =
        Market(baseUrl, apiKey, secretKey, showLimitUsage)

    override fun createSavings() =
        Savings(baseUrl, apiKey, secretKey, showLimitUsage)

    override fun createStaking() =
        Staking(baseUrl, apiKey, secretKey, showLimitUsage)

}
