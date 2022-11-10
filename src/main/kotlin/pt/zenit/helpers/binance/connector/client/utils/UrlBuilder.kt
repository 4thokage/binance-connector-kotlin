package pt.zenit.helpers.binance.connector.client.utils

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


object UrlBuilder {

    private const val MAX_DECIMAL_DIGITS = 30
    private var df: DecimalFormat? = null

    fun buildFullUrl(
        baseUrl: String,
        urlPath: String,
        parameters: LinkedHashMap<String?, Any>?,
        signature: String?
    ): String {
        return if (parameters != null && parameters.isNotEmpty()) {
            val sb = StringBuilder(baseUrl)
            sb.append(urlPath).append('?')
            joinQueryParameters(sb, parameters)
            if (null != signature) {
                sb.append("&signature=").append(signature)
            }
            sb.toString()
        } else {
            baseUrl + urlPath
        }
    }

    fun buildTimestamp(): String {
        return System.currentTimeMillis().toString()
    }

    private fun getFormatter(): DecimalFormat? {
        if (null == df) {
            // Overrides the default Locale
            val symbols = DecimalFormatSymbols(Locale.ENGLISH)
            df = DecimalFormat("#,##0.###", symbols)
            df!!.maximumFractionDigits = MAX_DECIMAL_DIGITS
            df!!.isGroupingUsed = false
        }
        return df
    }

    private fun urlEncode(s: String?): String? {
        return try {
            URLEncoder.encode(s, StandardCharsets.UTF_8.name())
        } catch (e: UnsupportedEncodingException) {
            // UTF-8 being unsuppored is unlikely
            // Replace with a unchecked exception to tidy up exception handling
            throw RuntimeException(StandardCharsets.UTF_8.name().toString() + " is unsupported", e)
        }
    }

    //concatenate query parameters
    fun joinQueryParameters(parameters: LinkedHashMap<String?, Any>?): String {
        return joinQueryParameters(StringBuilder(), parameters).toString()
    }

    private fun joinQueryParameters(urlPath: StringBuilder, parameters: LinkedHashMap<String?, Any>?): StringBuilder {
        if (parameters == null || parameters.isEmpty()) {
            return urlPath
        }
        var isFirst = true
        parameters.forEach {
            if (it.value is Double) {
                parameters.replace(it.key, getFormatter()!!.format(it.value))
            } else if (it.value is ArrayList<*> && (it.value as ArrayList<*>).isNotEmpty()) {
                joinArrayListParameters(it.key, urlPath, it.value as ArrayList<*>, isFirst)
                isFirst = false
            }

            if (isFirst) {
                isFirst = false
            } else {
                urlPath.append('&')
            }

            urlPath.append(it.key)
                .append('=')
                .append(urlEncode(it.value.toString()))
        }

        return urlPath
    }

    private fun joinArrayListParameters(
        key: String?,
        urlPath: StringBuilder,
        values: ArrayList<*>,
        isFirst: Boolean
    ) {
        for (value in values) {
            if (!isFirst) {
                urlPath.append('&')
            }
            urlPath.append(key)
                .append('=')
                .append(urlEncode(value.toString()))
        }
    }

}
