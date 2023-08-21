package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun longestNiceSubstring(s: String): String {
            val lower = Array(26) { TreeSet<Int>() }
            val higher = Array(26) { TreeSet<Int>() }

            s.forEachIndexed { index, c ->
                if (c in 'a'..'z') {
                    lower[c - 'a'].add(index)
                } else {
                    higher[c - 'A'].add(index)
                }
            }

            var result = ""

            fun dfs(left: Int, right: Int) {
                if (right - left + 1 <= result.length) {
                    return
                }

                var success = true
                for (char in 0 until 26) {
                    if (!success) {
                        break
                    }

                    val lowerSet = lower[char].subSet(left, true, right, true)
                    val higherSet = higher[char].subSet(left, true, right, true)

                    when {
                        lowerSet.isEmpty() && higherSet.isNotEmpty() -> {
                            var l = left
                            higherSet.forEach {
                                dfs(l, it - 1)
                                l = it + 1
                            }

                            dfs(l, right)
                            success = false
                        }

                        lowerSet.isNotEmpty() && higherSet.isEmpty() -> {
                            var l = left
                            lowerSet.forEach {
                                dfs(l, it - 1)
                                l = it + 1
                            }

                            dfs(l, right)
                            success = false
                        }
                    }
                }

                if (success) {
                    result = s.substring(left..right)
                }
            }

            dfs(0, s.lastIndex)

            return result
        }
    }

    expect {
        Solution().longestNiceSubstring(
            "YazaAay"
        )
    }
}
