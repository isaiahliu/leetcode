package p03xx

import util.expect

fun main() {
    class Solution {
        fun maxRotateFunction(nums: IntArray): Int {
            val sum = nums.sum()

            var max = 0
            nums.forEachIndexed { index, i ->
                max += i * index
            }

            var pre = max
            repeat(nums.lastIndex) {
                pre += sum
                pre -= nums[nums.size - it - 1] * nums.size

                max = max.coerceAtLeast(pre)
            }

            return max
        }
    }

    expect {
        Solution().maxRotateFunction(
            intArrayOf(4, 3, 2, 6)
        )
    }
}

