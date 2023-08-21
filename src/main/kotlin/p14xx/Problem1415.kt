package p14xx

import util.expect

fun main() {
    class Solution {
        fun getHappyString(n: Int, k: Int): String {
            var count = 0
            var result = ""
            fun dfs(prefix: String) {
                if (count == k) {
                    return
                } else if (prefix.length == n) {
                    if (++count == k) {
                        result = prefix
                    }
                } else {
                    val lastChar = prefix.getOrNull(prefix.lastIndex)

                    arrayOf('a', 'b', 'c').forEach {
                        if (it != lastChar) {
                            dfs(prefix + it)
                        }
                    }
                }
            }

            dfs("")

            return result
        }
    }

    expect {
        Solution().getHappyString(
            3, 9
        )
    }
}

