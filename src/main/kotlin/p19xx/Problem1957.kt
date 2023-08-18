package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun makeFancyString(s: String): String {
            return "(.)\\1{2,}".toRegex().replace(s, "$1$1")
        }
    }

    measureTimeMillis {
        Solution().makeFancyString(
            "leeeeeetcode"
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}