package p35xx

import util.expect

fun main() {
    class Solution {
        fun specialTriplets(nums: IntArray): Int {
            val forwardCounts = hashMapOf<Int, Int>()
            val backwardCounts = hashMapOf<Int, Int>()

            val forward = IntArray(nums.size)
            val backward = IntArray(nums.size)

            nums.indices.forEach {
                forward[it] = forwardCounts[nums[it] * 2] ?: 0
                forwardCounts[nums[it]] = (forwardCounts[nums[it]] ?: 0) + 1

                backward[nums.lastIndex - it] = backwardCounts[nums[nums.lastIndex - it] * 2] ?: 0
                backwardCounts[nums[nums.lastIndex - it]] = (backwardCounts[nums[nums.lastIndex - it]] ?: 0) + 1
            }

            var result = 0L
            val m = 1000000007

            nums.indices.forEach {
                result += 1L * forward[it] * backward[it]
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().specialTriplets(
            intArrayOf()
        )
    }
}
