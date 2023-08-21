package p17xx

import util.expect

fun main() {
    class Solution {
        fun minChanges(nums: IntArray, k: Int): Int {
            if (k == 1) {
                return nums.count { it != 0 }
            }

            val groups = Array(k) {
                hashMapOf<Int, Int>()
            }

            nums.forEachIndexed { index, num ->
                groups[index % k][num] = (groups[index % k][num] ?: 0) + 1
            }

            val dp = Array(k) {
                val kSize = nums.size / k + if (nums.size % k > it) 1 else 0
                IntArray(1 shl 10) { kSize }
            }

            groups[0].forEach { (key, value) ->
                dp[0][key] -= value
            }

            for (groupIndex in 1 until dp.size) {
                val lastDp = dp[groupIndex - 1]
                val lastMin = lastDp.min()
                val currentDp = dp[groupIndex]
                val kSize = nums.size / k + if (nums.size % k > groupIndex) 1 else 0

                currentDp.indices.forEach {
                    currentDp[it] = kSize + lastMin

                    groups[groupIndex].forEach { (key, value) ->
                        currentDp[it] = currentDp[it].coerceAtMost(lastDp[it xor key] + kSize - value)
                    }
                }

            }
            return dp[k - 1][0]
        }
    }

    expect {
        Solution().minChanges(
            intArrayOf(1, 2, 4, 1, 2, 5, 1, 2, 6), 3
        )
    }
}
