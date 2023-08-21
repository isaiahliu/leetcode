package p09xx

import util.expect

fun main() {
    class Solution {
        fun minDeletionSize(strs: Array<String>): Int {
            fun lte(index1: Int, index2: Int): Boolean {
                return strs.all {
                    it[index1] <= it[index2]
                }
            }

            val dp = IntArray(strs[0].length) { 1 }

            for (i in 1 until strs[0].length) {
                var max = 0

                for (target in i - 1 downTo 0) {
                    if (dp[target] > max && lte(target, i)) {
                        max = dp[target]
                    }
                }

                dp[i] = max + 1
            }

            return strs[0].length - dp.max()
        }
    }

    expect {
        Solution().minDeletionSize(
            arrayOf(
                "babca", "bbazb"
            )
        )
    }
}
