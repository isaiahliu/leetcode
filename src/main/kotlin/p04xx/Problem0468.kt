package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun validIPAddress(queryIP: String): String {
            var nodes = queryIP.split(".")

            if (nodes.size == 4) {
                if (nodes.all { it.toIntOrNull()?.takeIf { it < 256 }?.toString() == it }) {
                    return "IPv4"
                }
            }

            nodes = queryIP.split(":")

            if (nodes.size == 8) {
                if (nodes.all { it.takeIf { it.length <= 4 }?.toIntOrNull(16) != null }) {
                    return "IPv6"
                }
            }

            return "Neither"
        }
    }

    measureTimeMillis {
        Solution().validIPAddress(
            "2001:0db8:85a3:0:0:8A2E:0370:7334"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}