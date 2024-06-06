package p29xx

import util.expect

fun main() {
    class Solution {
        fun minimumSteps(s: String): Long {
            var result = 0L

            var nextIndex = 0
            s.forEachIndexed { index, c ->
                if (c == '0') {
                    result += index - nextIndex++
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumSteps(
            ""
        )
    }
}
