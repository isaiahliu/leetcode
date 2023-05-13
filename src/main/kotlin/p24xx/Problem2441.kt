package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMaxK(nums: IntArray): Int {
            val set = hashSetOf<Int>()

            var result = -1
            nums.forEach {
                if (-it in set) {
                    result = result.coerceAtLeast(it).coerceAtLeast(-it)
                }

                set.add(it)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findMaxK(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
