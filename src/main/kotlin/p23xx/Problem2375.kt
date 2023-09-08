package p23xx

import util.expect

fun main() {
    class Solution {
        fun smallestNumber(pattern: String): String {
            fun dfs(str: String): String? {
                if (str.length == pattern.length + 1) {
                    return str
                } else {
                    when (pattern.getOrNull(str.length - 1)) {
                        'I' -> str.last() + 1..'9'
                        'D' -> '1' until str.last()
                        else -> '1'..'9'
                    }.forEach {
                        if (it !in str) {
                            dfs(str + it)?.also {
                                return it
                            }
                        }
                    }

                    return null
                }
            }

            return dfs("").orEmpty()
        }
    }

    expect {
        Solution().smallestNumber(
            ""
        )
    }
}