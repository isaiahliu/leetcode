package p12xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun equalSubstring(s: String, t: String, maxCost: Int): Int {
            val delta = s.indices.map { (t[it] - s[it]).absoluteValue }

            var left = 0
            var right = -1

            var result = 0
            var sum = 0
            while (left < delta.size && right < delta.size) {
                if (right < left) {
                    right = left - 1
                    sum = 0
                }

                while (sum <= maxCost && right < delta.size) {
                    result = result.coerceAtLeast(right - left + 1)

                    right++
                    sum += delta.getOrNull(right) ?: break
                }

                sum -= delta[left++]
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().equalSubstring(
            "krpgjbjjznpzdfy",
            "nxargkbydxmsgby",
            14
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
