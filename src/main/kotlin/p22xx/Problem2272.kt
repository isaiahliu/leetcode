package p22xx

import util.expect

fun main() {
    class Solution {
        fun largestVariance(s: String): Int {
            var result = 0

            val CURRENT_ONLY = 0
            val STARTS_WITH_TARGET = 1
            val STARTS_WITH_CURRENT = 2
            repeat(26) {
                val minChar = 'a' + it

                val otherCounts = IntArray(26)
                val status = IntArray(26)
                s.forEach {
                    if (it == minChar) {
                        repeat(26) {
                            if (status[it] == STARTS_WITH_TARGET) {
                                status[it] = STARTS_WITH_CURRENT
                            } else {
                                otherCounts[it]--
                            }

                            if (otherCounts[it] >= 0) {
                                if (status[it] == CURRENT_ONLY) {
                                    status[it] = STARTS_WITH_CURRENT
                                }

                                result = result.coerceAtLeast(otherCounts[it])
                            } else {
                                status[it] = STARTS_WITH_TARGET
                                otherCounts[it] = -1
                            }
                        }
                    } else {
                        otherCounts[it - 'a']++

                        if (status[it - 'a'] != CURRENT_ONLY) {
                            result = result.coerceAtLeast(otherCounts[it - 'a'])
                        }
                    }
                }
            }

            return result
        }
    }

    expect(3) {
        Solution().largestVariance(
            "icexiahccknibwuwgi"
        )
    }

    expect(3) {
        Solution().largestVariance(
            "aababbb"
        )
    }
}