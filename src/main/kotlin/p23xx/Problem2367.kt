package p23xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arithmeticTriplets(nums: IntArray, diff: Int): Int {
            val queue = IntArray(201)

            nums.forEach { n ->
                queue.getOrNull(n - diff)?.also {
                    queue[n] = it
                    queue[n - diff] = 0
                }
                queue[n]++
            }

            return queue.map { it - 2 }.filter { it > 0 }.sum()
        }
    }

    measureTimeMillis {
        Solution().arithmeticTriplets(
            intArrayOf(4, 5, 6, 7, 8, 9), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}