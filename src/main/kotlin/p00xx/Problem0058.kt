package p00xx

import util.expect

fun main() {
    class Solution {
        fun lengthOfLastWord(s: String): Int {
            var result = 0
            var sum = 0
            ("$s ").forEach {
                if (it == ' ') {
                    if (sum > 0) {
                        result = sum
                    }
                    sum = 0
                } else {
                    sum++
                }
            }

            return result
        }
    }

    expect {
        Solution().lengthOfLastWord("Hello World")
    }
}


