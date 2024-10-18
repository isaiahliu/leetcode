package p31xx

import util.expect

fun main() {
    class Solution {
        fun numberOfPermutations(n: Int, requirements: Array<IntArray>): Int {
            val m = 1000000007

            val req = IntArray(n) { -1 }
            var maxCount = 0
            req[0] = 0
            for ((num, count) in requirements) {
                req[num] = count
                maxCount = maxOf(maxCount, count)
            }

            if (req[0] != 0) {
                return 0
            }

            val dp = Array(n) { IntArray(maxCount + 1) { -1 } }
            fun dfs(end: Int, count: Int): Int {
                return when {
                    end == 0 -> {
                        1
                    }

                    dp[end][count] != -1 -> {
                        dp[end][count]
                    }

                    req[end - 1] >= 0 -> {
                        val r = req[end - 1]

                        if (r <= count && count <= end + r) {
                            dfs(end - 1, r)
                        } else {
                            0
                        }.also { dp[end][count] = it }
                    }

                    else -> {
                        var sm = 0
                        for (i in 0..minOf(end, count)) {
                            sm = (sm + dfs(end - 1, count - i)) % m
                        }
                        sm.also { dp[end][count] = it }
                    }
                }
            }

            return dfs(n - 1, req[n - 1])
        }
    }

    expect {
        Solution().numberOfPermutations(
            3, arrayOf(
                intArrayOf(2, 2), intArrayOf(0, 0)
            )
        )
    }
}
