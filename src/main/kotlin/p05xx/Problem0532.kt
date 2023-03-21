package p05xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findPairs(nums: IntArray, k: Int): Int {
            if (k == 0) {
                return nums.toList().groupingBy { it }.eachCount().values.map { (it - 1).sign }.sum()
            }

            val set = hashSetOf<Int>()

            var result = 0
            nums.sorted().forEach { num ->
                if (set.add(num)) {
                    if (num - k in set) {
                        result++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findPairs(intArrayOf(), 1).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}