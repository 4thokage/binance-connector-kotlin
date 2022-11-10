package pt.zenit.helpers.binance.connector.client.utils

import pt.zenit.helpers.binance.connector.client.exceptions.BinanceConnectorException


object ParameterChecker {

    fun checkParameter(parameters: LinkedHashMap<String?, Any>, parameter: String?, t: Class<*>) {
        checkRequiredParameter(parameters, parameter)
        checkParameterType(parameters[parameter]!!, t, parameter)
    }

    fun checkRequiredParameter(parameters: LinkedHashMap<String?, Any>, parameter: String?) {
        if (!parameters.containsKey(parameter)) {
            throw BinanceConnectorException(String.format("\"%s\" is a mandatory parameter!", parameter))
        }
    }

    fun checkParameterType(parameter: Any?, t: Class<*>, name: String?) {
        if (!t.isInstance(parameter)) {
            throw BinanceConnectorException(String.format("\"%s\" must be of %s type.", name, t))
        } else if (t == String::class.java && parameter.toString().trim { it <= ' ' } == "") {
            throw BinanceConnectorException(String.format("\"%s\" must not be empty.", name))
        }
    }
}
