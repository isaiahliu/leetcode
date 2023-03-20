package p05xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMinMoves(machines: IntArray): Int {
            val sum = machines.sum()
            if (sum % machines.size != 0) {
                return -1
            }

            val avg = sum / machines.size

            var result = 0
            var t = 0
            for (num in machines) {
                val n = num - avg
                t += n
                result = result.coerceAtLeast(t.absoluteValue.coerceAtLeast(n))
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().findMinMoves(
            intArrayOf(1, 0, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}