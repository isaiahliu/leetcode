package p27xx

import util.expect

fun main() {
    class Solution {
        fun minExtraChar(s: String, dictionary: Array<String>): Int {
            val set = dictionary.toSet()

            val cache = IntArray(s.length) { -1 }

            fun dfs(index: Int): Int {
                return if (index == s.length) {
                    0
                } else if (cache[index] >= 0) {
                    cache[index]
                } else {
                    var result = 1 + dfs(index + 1)

                    buildString {

                        for (i in index until s.length) {
                            append(s[i])

                            if (toString() in set) {
                                result = minOf(result, dfs(i + 1))
                            }
                        }
                    }

                    cache[index] = result

                    result
                }
            }

            return dfs(0)
        }
    }

    expect {
        Solution().minExtraChar(
            "leetscode", arrayOf("leet", "code", "leetcode")
        )
    }
}
