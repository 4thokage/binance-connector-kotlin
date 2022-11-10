package pt.zenit.helpers.binance.connector.client.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class UrlBuilderTest {
    private val baseUrl = "www.test.com"
    private val urlPath = "/url/path"
    private val doubleValue = 0.0006
    private val extensiveDecimalsDouble = 1.123456789101112
    private val highDouble = 10000.1
    private val intValue = 2
    private val mockParameters: LinkedHashMap<String?, Any> = object : LinkedHashMap<String?, Any>() {
        init {
            put("key1", "value1")
            put("key2", intValue)
            put("key3", doubleValue)
        }
    }
    private val mockDoubleParameters: LinkedHashMap<String?, Any> = object : LinkedHashMap<String?, Any>() {
        init {
            put("key1", extensiveDecimalsDouble)
            put("key2", highDouble)
        }
    }
//    private val mockStreams: ArrayList<String?> = object : ArrayList<String?>() {
//        init {
//            add("stream1")
//            add("stream2")
//            add("stream3")
//        }
//    }

    @Test
    fun testBuildFullUrl() {
        val fullUrl = "www.test.com/url/path?key1=value1&key2=2&key3=0.0006"
        assertEquals(fullUrl, UrlBuilder.buildFullUrl(baseUrl, urlPath, mockParameters, null))
    }

    @Test
    fun testBuildFullUrlWithoutParams() {
        assertEquals(baseUrl + urlPath, UrlBuilder.buildFullUrl(baseUrl, urlPath, null, null))
    }

    @Test
    fun testJoinQueryParameters() {
        val joinedQuery = "key1=value1&key2=2&key3=0.0006"
        assertEquals(joinedQuery, UrlBuilder.joinQueryParameters(mockParameters))
    }

    @Test
    fun testJoinLargeQueryParameters() {
        mockParameters["key4"] = extensiveDecimalsDouble
        mockParameters["key5"] = highDouble
        val joinedQuery = "key1=value1&key2=2&key3=0.0006&key4=1.123456789101112&key5=10000.1"
        assertEquals(joinedQuery, UrlBuilder.joinQueryParameters(mockParameters))
    }

    @Test
    fun testJoinQueryParametersWithoutParams() {
        assertEquals("", UrlBuilder.joinQueryParameters(null))
    }

    /**
     * Tests the JoinQueryParameters with Locale.IT to see if the originated Double continues with "." instead of being changed to a ","
     * Also tests if there's no drop of 0s, add of group separator "," and limitation on decimals number.
     */
    @Test
    fun testJoinQueryParametersWithLocaleIT() {
        Locale.setDefault(Locale("it", "IT"))
        val joinedQuery = String.format("key1=%s&key2=%s", extensiveDecimalsDouble, highDouble)
        val buildQuery = UrlBuilder.joinQueryParameters(mockDoubleParameters)
        assertEquals(joinedQuery, buildQuery)
    }

//    @Test
//    fun testBuildStreamUrl() {
//        val streamUrl = "www.test.com?streams=stream1/stream2/stream3"
//        assertEquals(streamUrl, UrlBuilder.buildStreamUrl(baseUrl, mockStreams))
//    }
}
