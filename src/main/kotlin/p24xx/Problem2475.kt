package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun unequalTriplets(nums: IntArray): Int {
            return nums.toList().groupingBy { it }.eachCount().values.fold(0 to 0) { (visited, result), current ->
                (visited + current).let {
                    it to (result + current * visited * (nums.size - it))
                }
            }.second
        }
    }

    measureTimeMillis {
        Solution().unequalTriplets(
            intArrayOf(1, 4, 4, 5, 5)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
