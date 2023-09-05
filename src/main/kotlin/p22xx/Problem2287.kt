package p22xx

import util.expect

fun main() {
    class Solution {
        fun rearrangeCharacters(s: String, target: String): Int {
            var result = Int.MAX_VALUE
            val group1 = s.groupingBy { it }.eachCount()
            target.groupingBy { it }.eachCount().forEach { (ch, count) ->
                val total = group1[ch] ?: 0

                result = result.coerceAtMost(total / count)
            }

            return result
        }
    }

    expect {
        Solution().rearrangeCharacters(
            "", ""
        )
    }
}