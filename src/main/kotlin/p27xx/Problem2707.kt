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
                    var result = dfs(index + 1)

                    val str = StringBuilder()

                    for (i in index until s.length) {
                        str.append(s[i])

                        if (str.toString() in set) {
                            result = maxOf(result, i - index + 1 + dfs(i + 1))
                        }
                    }

                    cache[index] = result

                    result
                }
            }

            return s.length - dfs(0)
        }
    }

    expect {
        Solution().minExtraChar(
            "leetscode", arrayOf("leet", "code", "leetcode")
        )
    }
}
