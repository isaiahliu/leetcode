package p32xx

import util.expect

fun main() {
    class Solution {
        fun validStrings(n: Int): List<String> {
            val result = arrayListOf<String>()

            fun dfs(s: String) {
                if (s.length == n) {
                    result += s
                } else {
                    dfs("${s}1")
                    if (s.lastOrNull() != '0') {
                        dfs("${s}0")
                    }
                }
            }

            dfs("")

            return result
        }
    }

    expect {
        Solution().validStrings(
            3
        )
    }
}
