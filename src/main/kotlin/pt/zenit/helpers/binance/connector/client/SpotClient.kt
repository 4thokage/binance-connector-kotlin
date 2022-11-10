package pt.zenit.helpers.binance.connector.client

import pt.zenit.helpers.binance.connector.client.impl.spot.Market
import pt.zenit.helpers.binance.connector.client.impl.spot.Savings
import pt.zenit.helpers.binance.connector.client.impl.spot.Staking

interface SpotClient {

    fun createMarket(): Market
    fun createSavings(): Savings
    fun createStaking(): Staking
}
