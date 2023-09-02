package p22xx

import util.expect

fun main() {
    class Solution {
        fun digitSum(s: String, k: Int): String {
            var result = s

            while (result.length > k) {
                val next = StringBuilder()

                var sum = 0
                result.forEachIndexed { index, c ->
                    sum += c - '0'

                    if ((index + 1) % k == 0 || index == result.lastIndex) {
                        next.append(sum.toString())
                        sum = 0
                    }
                }

                result = next.toString()
            }

            return result
        }
    }
    expect {
        Solution().digitSum(
            "11111222223", 3
        )
    }
}