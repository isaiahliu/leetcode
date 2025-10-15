package p33xx

import util.expect

fun main() {
    class Solution {
        fun maxIncreasingSubarrays(nums: List<Int>): Int {
            val increase = IntArray(nums.size) { 1 }

            (1 until nums.size).forEach {
                if (nums[it] > nums[it - 1]) {
                    increase[it] = increase[it - 1] + 1
                }
            }

            var result = 0
            var decreaseSize = 1
            (nums.lastIndex - 1 downTo 0).forEach {
                result = maxOf(result, minOf(increase[it], decreaseSize))
                if (nums[it] >= nums[it + 1]) {
                    decreaseSize = 0
                }

                decreaseSize++
            }

            return result
        }
    }

    expect {
        Solution().maxIncreasingSubarrays(
            listOf()
        )
    }
}
