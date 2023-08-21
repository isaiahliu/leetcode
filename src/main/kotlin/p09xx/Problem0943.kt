package p09xx

import util.expect

fun main() {
    class Solution {
        fun shortestSuperstring(words: Array<String>): String {
            val map = hashMapOf<Pair<String, String>, String>()
            fun common(left: String, right: String): String {
                val cacheKey = left to right
                if (cacheKey in map) {
                    return map[cacheKey].orEmpty()
                }
                var commonSize = left.length.coerceAtMost(right.length)

                while (left.takeLast(commonSize) != right.take(commonSize)) {
                    commonSize--
                }

                val result = left + right.drop(commonSize)
                map[cacheKey] = result

                return result
            }

            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val dp = arrayOfNulls<Set<String>>(1 shl words.size)

            for (status in 1 until dp.size) {
                val result = hashSetOf<String>()
                status.forEachBit { pos ->
                    val from = status - (1 shl pos)

                    if (from == 0) {
                        result += words[pos]
                    } else {
                        var min = ""
                        dp[from]?.forEach {
                            val pre = common(it, words[pos])

                            if (min.isEmpty() || pre.length < min.length) {
                                min = pre
                            }
                        }

                        result += min
                    }
                }
                dp[status] = result
            }

            return dp[dp.lastIndex]?.minBy { it.length }.orEmpty()
        }
    }

    expect {
        Solution().shortestSuperstring(
            arrayOf(
                "aaa", "baab", "bcba", "cda"
            )
        )
    }
}
