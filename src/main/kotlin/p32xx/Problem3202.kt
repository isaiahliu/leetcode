package p32xx

import util.expect

fun main() {
    class Solution {
        fun maximumLength(nums: IntArray, k: Int): Int {
            val dp = Array(k) { IntArray(k) }

            nums.forEach {
                (it % k).also { m ->
                    repeat(k) {
                        dp[it][m] = dp[m][it] + 1
                    }
                }
            }

            return dp.maxOf { it.max() }
        }
    }

    expect {
        Solution().maximumLength(intArrayOf(), 1)
    }
}
