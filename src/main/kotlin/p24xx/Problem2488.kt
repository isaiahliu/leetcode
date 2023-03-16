package p24xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSubarrays(nums: IntArray, k: Int): Int {
            val kIndex = nums.indexOf(k).takeIf { it >= 0 } ?: return 0

            var result = 0

            var count = 0

            val countMap = hashMapOf(0 to 1)

            nums.forEachIndexed { index, num ->
                when {
                    index < kIndex -> {
                        val targetIndex = kIndex - index - 1

                        count += (nums[targetIndex] - k).sign

                        countMap[count] = (countMap[count] ?: 0) + 1
                    }

                    else -> {
                        if (index == kIndex) {
                            count = 0
                        } else {
                            count += (num - k).sign
                        }

                        countMap[-count]?.also { result += it }
                        countMap[-count + 1]?.also { result += it }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countSubarrays(
            intArrayOf(
                3, 1, 4, 5
            ), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}