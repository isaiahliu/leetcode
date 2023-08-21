package p01xx

import util.expect

fun main() {
    class Solution {
        fun convertToTitle(columnNumber: Int): String {
            var result = ""

            var t = columnNumber
            while (true) {
                t--

                result = "${'A' + t % 26}${result}"

                if (t < 26) {
                    break
                }

                t /= 26
            }

            return result
        }
    }
    expect {
        repeat(100) {
            Solution().convertToTitle(it + 1)
        }
    }
}

