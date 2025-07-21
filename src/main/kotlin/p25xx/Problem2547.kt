package p25xx

import util.expect

fun main() {
    class Solution {
        fun minCost(nums: IntArray, k: Int): Int {
            val weights = Array(nums.size) { from ->
                val counts = hashMapOf<Int, Int>()
                var weight = 0
                IntArray(nums.size) { to ->
                    if (to >= from) {
                        counts[nums[to]] = (counts[nums[to]] ?: 0) + 1

                        when (counts[nums[to]]) {
                            1 -> {}
                            2 -> weight += 2
                            else -> weight++
                        }

                        weight
                    } else {
                        0
                    }
                }
            }

            val dp = IntArray(nums.size) { k + weights[0][it] }

            nums.indices.forEach { end ->
                for (lastGroupIndex in 0 until end) {
                    dp[end] = minOf(dp[end], dp[lastGroupIndex] + k + weights[lastGroupIndex + 1][end])
                }
            }
            return dp.last()
        }
    }

    expect {
        Solution().minCost(
            intArrayOf(1, 2, 1, 2, 1, 3, 3), 2
        )
    }
}

