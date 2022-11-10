package pt.zenit.helpers.binance.connector.client.exceptions

class BinanceServerException(message: String, val statusCode: Number) : RuntimeException(message)
