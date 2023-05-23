package p09xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxTurbulenceSize(arr: IntArray): Int {
            var direction = ((arr.getOrNull(1) ?: return 1) - arr[0]).sign
            var length = direction * direction + 1
            var result = length

            for (i in 2 until arr.size) {
                val d = (arr[i] - arr[i - 1]).sign

                if (direction * d == -1) {
                    length++
                } else {
                    length = d * d + 1
                }

                direction = d
                result = result.coerceAtLeast(length)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxTurbulenceSize(
            intArrayOf(9, 4, 2, 10, 7, 8, 8, 1, 9)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
