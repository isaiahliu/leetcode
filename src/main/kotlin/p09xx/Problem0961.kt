package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun repeatedNTimes(nums: IntArray): Int {
            val visited = hashSetOf<Int>()
            nums.forEach {
                if (!visited.add(it)) {
                    return it
                }
            }
            return 0
        }
    }

    measureTimeMillis {
        Solution().repeatedNTimes(
            intArrayOf(1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
