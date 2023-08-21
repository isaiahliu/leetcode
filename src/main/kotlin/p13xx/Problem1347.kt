package p13xx

import util.expect

fun main() {
    class Solution {
        fun minSteps(s: String, t: String): Int {
            val counts = t.groupingBy { it }.eachCount()
            var result = 0
            s.groupingBy { it }.eachCount().forEach { (char, count) ->
                val target = counts[char] ?: 0

                if (count > target) {
                    result += count - target
                }
            }

            return result
        }
    }

    expect {
        Solution().minSteps(
            "", ""
        )
    }
}

