package p15xx

import util.expect

fun main() {
    class Solution {
        fun longestAwesome(s: String): Int {
            val p = IntArray(10) {
                1 shl it
            }

            var status = 0
            val map = hashMapOf(0 to -1)

            var result = 1
            s.forEachIndexed { index, c ->
                val pos = 1 shl (c - '0')

                status = status xor pos

                map[status]?.also {
                    result = result.coerceAtLeast(index - it)
                }

                p.forEach {
                    map[status xor it]?.also {
                        result = result.coerceAtLeast(index - it)
                    }
                }

                map[status] = map[status] ?: index
            }

            return result
        }
    }

    expect {
        Solution().longestAwesome(
            "213123"
        )
    }
}

