package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumProduct(nums: IntArray): Int {
            val pos = nums.filter { it > 0 }.sortedDescending()
            val neg = nums.filter { it < 0 }.sortedDescending()
            val zero = nums.any { it == 0 }

            var posMax = 0
            if (pos.size >= 3) {
                posMax = pos[0] * pos[1] * pos[2]
            }

            if (pos.isNotEmpty() && neg.size >= 2) {
                posMax = posMax.coerceAtLeast(pos[0] * neg[neg.lastIndex] * neg[neg.lastIndex - 1])
            }

            if (posMax > 0) {
                return posMax
            }

            if (zero) {
                return 0
            }

            return neg[0] * neg[1] * neg[2]
        }
    }

    measureTimeMillis {
        Solution().maximumProduct(intArrayOf()).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}