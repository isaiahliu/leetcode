package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val counts = hashMapOf<Int, Int>()

            nums.forEach {
                counts[it] = (counts[it] ?: 0) + 1
            }

            return counts.entries.sortedByDescending { it.value }.take(k).map { it.key }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().topKFrequent(
            intArrayOf(), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

