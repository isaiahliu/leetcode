package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Codec {
        var id = 0

        val urls = arrayListOf<String>()

        // Encodes a URL to a shortened URL.
        fun encode(longUrl: String): String {
            urls.add(longUrl)
            return id.toString().also { id++ }
        }

        // Decodes a shortened URL to its original URL.
        fun decode(shortUrl: String): String {
            return urls[shortUrl.toInt()]
        }
    }

    measureTimeMillis {
        Codec().encode("").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}