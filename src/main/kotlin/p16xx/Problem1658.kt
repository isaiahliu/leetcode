package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperations(nums: IntArray, x: Int): Int {
            val map = hashMapOf(0 to -1)
            var sum = 0

            nums.forEachIndexed { index, num ->
                sum += num

                map[sum] = index
            }

            sum = 0

            var result = map[x]?.let { it + 1 } ?: Int.MAX_VALUE
            for (index in nums.lastIndex downTo 0) {
                sum += nums[index]

                map[x - sum]?.takeIf { it < index }?.also {
                    result = result.coerceAtMost(nums.size - index + it + 1)
                }
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    measureTimeMillis {
        Solution().minOperations(intArrayOf(), 1).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

