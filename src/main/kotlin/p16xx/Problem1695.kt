package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumUniqueSubarray(nums: IntArray): Int {
            var sum = 0
            val sums = IntArray(nums.size) {
                sum += nums[it]
                sum
            }

            var left = -1
            val map = hashMapOf<Int, Int>()
            var result = 0
            nums.forEachIndexed { index, num ->
                map[num]?.also {
                    left = left.coerceAtLeast(it)
                }

                result = result.coerceAtLeast(sums[index] - sums.getOrElse(left) { 0 })

                map[num] = index
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maximumUniqueSubarray(
            intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
