package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumMountainRemovals(nums: IntArray): Int {
            val map = TreeMap<Int, Int>()
            map[nums[0]] = 1
            val leftSize = IntArray(nums.size)

            for (index in 1 until nums.size) {
                ((map.headMap(nums[index]).values.maxOrNull() ?: 0) + 1).also {
                    leftSize[index] = it
                    map[nums[index]] = it
                }
            }

            map.clear()
            map[nums[nums.lastIndex]] = 1

            var result = nums.size

            for (index in nums.lastIndex - 1 downTo 1) {
                map[nums[index]] = (map.headMap(nums[index]).values.maxOrNull()?.also {
                    if (leftSize[index] > 1) {
                        result = result.coerceAtMost(nums.size - leftSize[index] - it)
                    }
                } ?: 0) + 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minimumMountainRemovals(
            intArrayOf(100, 92, 89, 77, 74, 66, 64, 66, 64)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

