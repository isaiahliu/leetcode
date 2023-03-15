package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun licenseKeyFormatting(s: String, k: Int): String {
            val str = StringBuilder()

            for (i in s.length - 1 downTo 0) {
                val c = s[i]
                if (c == '-') {
                    continue
                }

                if (str.length % (k + 1) == k) {
                    str.append("-")
                }

                str.append(c.uppercaseChar())
            }

            return str.toString().reversed()
        }
    }

    measureTimeMillis {
        Solution().licenseKeyFormatting(
            "2-5g-3-J", 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}