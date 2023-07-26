package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun entityParser(text: String): String {
            return text.replace("&quot;", "\"")
                .replace("&apos;", "'")
                .replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&frasl;", "/")
                .replace("&amp;", "&")
        }
    }

    measureTimeMillis {
        Solution().entityParser(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

