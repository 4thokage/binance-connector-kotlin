package pt.zenit.helpers.binance.connector.client.exceptions

class BinanceClientException(message: String, val errorMsg: String?, val statusCode: Number,  val errorCode: Number?) :
    RuntimeException(message) {
    constructor(message: String, statusCode: Number) : this(message, null, statusCode, null)
}
