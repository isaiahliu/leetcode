package p31xx

import util.expect

fun main() {
    class Solution {
        fun maximumEnergy(energy: IntArray, k: Int): Int {
            val dp = IntArray(k)

            energy.forEachIndexed { index, value ->
                dp[index % k] = value + maxOf(dp[index % k], 0)
            }

            return dp.max()
        }
    }

    expect {
        Solution().maximumEnergy(
            intArrayOf(5, 2, -10, -5, 1), 3
        )
    }
}
