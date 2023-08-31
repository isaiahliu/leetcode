package p22xx

import util.expect

fun main() {
    class Solution {
        fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
            val totalSize = piles.sumOf { it.size }

            var pickCount = k
            var larger = true
            if (k * 2 > totalSize) {
                pickCount = totalSize - k
                larger = false
            }

            val dp = hashMapOf(0 to 0)

            if (pickCount > 0) {
                piles.forEach { l ->
                    dp.toMap().forEach { (count, s1) ->
                        var s2 = 0
                        for (index in l.indices) {
                            s2 += if (larger) {
                                l[index]
                            } else {
                                l[l.lastIndex - index]
                            }

                            val totalCount = count + index + 1

                            if (totalCount > k) {
                                break
                            }

                            dp[totalCount] = dp[totalCount]?.takeIf {
                                if (larger) {
                                    it > s1 + s2
                                } else {
                                    it < s1 + s2
                                }
                            } ?: (s1 + s2)
                        }
                    }
                }
            }

            return dp[pickCount]?.let {
                if (larger) {
                    it
                } else {
                    piles.sumOf { it.sum() } - it
                }
            } ?: 0
        }
    }

    expect {
        Solution().maxValueOfCoins(
            listOf(
                listOf(1, 100, 3),
                listOf(7, 8, 9),
            ), 2
        )
    }
}