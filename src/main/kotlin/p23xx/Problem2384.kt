package p23xx

import util.expect

fun main() {
    class Solution {
        fun largestPalindromic(num: String): String {
            val lastPart = StringBuilder()
            var mid = ""
            num.groupingBy { it }.eachCount().toSortedMap().forEach { (ch, count) ->
                repeat(count / 2) {
                    lastPart.append(ch)
                }

                if (count % 2 == 1) {
                    mid = ch.toString()
                }
            }

            if (lastPart.lastOrNull() == '0') {
                lastPart.clear()

                if (mid.isEmpty()) {
                    mid = "0"
                }
            }

            return "${lastPart.toString().reversed()}$mid$lastPart"
        }
    }

    expect {
        Solution().largestPalindromic(
            ""
        )
    }
}