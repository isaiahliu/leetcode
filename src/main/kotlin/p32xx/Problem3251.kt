package p32xx

import util.expect

fun main() {
    class Solution {
        fun countOfPairs(nums: IntArray): Int {
            val m = 1000000007

            var dp = IntArray(nums[0] + 1) { it + 1 }

            for (index in 1 until nums.size) {
                var sum = 0
                dp = IntArray(nums[index] + 1) { cur ->
                    dp.getOrNull(minOf(nums[index - 1] - nums[index], 0) + cur)?.also {
                        sum += it
                        sum %= m
                    }

                    sum
                }
            }

            return dp.last()
        }
    }

    expect {
        Solution().countOfPairs(
            intArrayOf()
        )
    }
}
