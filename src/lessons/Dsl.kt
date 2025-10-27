package lessons

data class HttpRequest(
    val method: String,
    val url: String,
    val headers: MutableMap<String, String>,
    val body: String? = null

)

class HeaderBuilder {
    private val headers = mutableMapOf<String, String>()

    fun contentType(value: String){
        headers["Content-Type"] = value
    }

    fun authorization(value: String) {
        headers["Authorization"] = value
    }

    fun build(): MutableMap<String, String> = headers
}

class HttpRequestBuilder(private val method: String) {
    private var url: String = ""
    private var headers: MutableMap<String, String> = mutableMapOf()
    private var body: String? = null

    fun url (value: String) {
        url = value
    }

    fun header (block: HeaderBuilder.() -> Unit) {
        val headerBuilder = HeaderBuilder().apply(block)
        headers.putAll(headerBuilder.build())
    }

    fun body (value: String) {
        body = value
    }

    fun build(): HttpRequest {
        return HttpRequest(
            method = method,
            url = url,
            headers = headers,
            body = body
        )
    }
}

fun get(block: HttpRequestBuilder.() -> Unit): HttpRequest =
    HttpRequestBuilder("GET").apply(block).build()

fun post(block: HttpRequestBuilder.() -> Unit): HttpRequest =
    HttpRequestBuilder("POST").apply(block).build()

fun put(block: HttpRequestBuilder.() -> Unit): HttpRequest =
    HttpRequestBuilder("PUT").apply(block).build()

fun delete(block: HttpRequestBuilder.() -> Unit): HttpRequest =
    HttpRequestBuilder("DELETE").apply(block).build()


fun main() {
    val request = post {
        url("https://example.com/api")
        header {
            contentType("application/json")
            authorization("Bearer token")
        }
        body("""{"name": "John", "age": 30}""")
    }

    println(request)
}