package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun defangIPaddr(address: String): String {
            return address.replace(".", "[.]")
        }
    }

    measureTimeMillis {
        Solution().defangIPaddr(
            "1.1.1.1"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}