package pt.zenit.helpers.binance.connector.client.exceptions

class BinanceClientException(message: String, errorMsg: String?, statusCode: Number, errorCode: Number?) :
    RuntimeException(message) {
    constructor(message: String, statusCode: Number) : this(message, null, statusCode, null)
}
