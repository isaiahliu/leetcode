package p33xx

import util.expect

fun main() {
    class Solution {
        fun hasIncreasingSubarrays(nums: List<Int>, k: Int): Boolean {
            val increase = IntArray(nums.size) { 1 }

            (1 until nums.size).forEach {
                if (nums[it] > nums[it - 1]) {
                    increase[it] = increase[it - 1] + 1
                }
            }

            var decreaseSize = 1
            (nums.lastIndex - 1 downTo 0).forEach {
                if (increase[it] >= k && decreaseSize >= k) {
                    return true
                }
                if (nums[it] >= nums[it + 1]) {
                    decreaseSize = 0
                }

                decreaseSize++
            }

            return false
        }
    }

    expect {
        Solution().hasIncreasingSubarrays(
            listOf(), 1
        )
    }
}
