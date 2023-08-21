package p24xx

import util.expect

fun main() {
    class Solution {
        fun oddString(words: Array<String>): String {
            for (i in 1 until words[0].length) {
                val map = words.groupBy {
                    it[i] - it[i - 1]
                }

                if (map.size > 1) {
                    return map.values.firstOrNull { it.size == 1 }?.firstOrNull().orEmpty()
                }
            }

            return ""
        }
    }

    expect {
        Solution().oddString(
            arrayOf()
        )
    }
}
