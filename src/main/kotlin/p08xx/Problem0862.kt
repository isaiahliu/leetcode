package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestSubarray(nums: IntArray, k: Int): Int {
            var result = Int.MAX_VALUE
            val map = TreeMap<Long, Int>()
            map[0] = -1

            var sum = 0L
            for (i in nums.indices) {
                if (nums[i] >= k) {
                    return 1
                }

                sum += nums[i]

                val target = sum - k
                map.lowerEntry(target + 1)?.also { (_, value) ->
                    result = result.coerceAtMost(i - value)
                }

                while (true) {
                    val key = map.higherKey(sum - 1) ?: break
                    map.remove(key)
                }
                map[sum] = i
            }
            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    measureTimeMillis {
        Solution().shortestSubarray(
            intArrayOf(
                3, -12, -50, 51, 100, -47, 99, 34, 14, -13, 89, 31, -14, -44, 23, -38, 6
            ), 151
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}