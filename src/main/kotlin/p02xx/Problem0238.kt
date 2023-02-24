package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun productExceptSelf(nums: IntArray): IntArray {
            val result = IntArray(nums.size) { 1 }

            var p = 1

            for (i in 1 until result.size) {
                p *= nums[i - 1]

                result[i] = p
            }

            p = 1

            for (i in result.size - 2 downTo 0) {
                p *= nums[i + 1]
                result[i] *= p
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().productExceptSelf(intArrayOf(1, 2, 3, 4)).also { println(it) }
    }
}

