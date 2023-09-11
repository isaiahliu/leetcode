package p24xx

import util.expect

fun main() {
    class Solution {
        fun goodIndices(nums: IntArray, k: Int): List<Int> {
            val decCounts = IntArray(nums.size) { 1 }
            val incCounts = IntArray(nums.size) { 1 }

            for (i in 1 until nums.size) {
                if (nums[i] <= nums[i - 1]) {
                    decCounts[i] = decCounts[i - 1] + 1
                }

                if (nums[nums.lastIndex - i] <= nums[nums.lastIndex - i + 1]) {
                    incCounts[nums.lastIndex - i] = incCounts[nums.lastIndex - i + 1] + 1
                }
            }

            return (k until nums.size - k).filter {
                decCounts[it - 1] >= k && incCounts[it + 1] >= k
            }
        }
    }

    expect {
        Solution().goodIndices(
            intArrayOf(), 1
        )
    }
}