package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun frequencySort(nums: IntArray): IntArray {
            val result = arrayListOf<Int>()

            nums.toList().groupingBy { it }
                .eachCount().entries.sortedWith(compareBy<Map.Entry<Int, Int>> { it.value }.thenByDescending { it.key })
                .forEach { (key, value) ->
                    repeat(value) {
                        result.add(key)
                    }
                }

            return result.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().frequencySort(
            intArrayOf(1, 1, 2, 2, 2, 3),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}