package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestSubarray(nums: IntArray, limit: Int): Int {
            val count = TreeMap<Int, Int>()

            fun add(num: Int) {
                count[num] = (count[num] ?: 0) + 1
            }

            fun del(num: Int) {
                count[num]?.also {
                    if (it == 1) {
                        count.remove(num)
                    } else {
                        count[num] = it - 1
                    }
                }
            }

            fun match(num: Int): Boolean {
                if (count.isEmpty()) {
                    return true
                }

                val low = count.firstKey()
                val high = count.lastKey()

                return num in (low - limit)..(low + limit) && num in (high - limit)..(high + limit)
            }

            add(nums[0])
            var left = 0
            var right = 0

            var result = 1
            while (right < nums.lastIndex) {
                val num = nums[++right]

                while (!match(num)) {
                    del(nums[left++])
                }

                result = result.coerceAtLeast(right - left + 1)
                add(num)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestSubarray(
            intArrayOf(8, 2, 4, 7), 4
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

