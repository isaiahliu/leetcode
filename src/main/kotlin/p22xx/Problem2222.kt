package p22xx

import util.expect

fun main() {
    class Solution {
        fun numberOfWays(s: String): Long {
            var count = 0
            val leftZeros = IntArray(s.length) { index ->
                count.also {
                    if (s[index] == '0') {
                        count++
                    }
                }
            }

            val right = longArrayOf(0, 0)

            var result = 0L
            for (index in s.lastIndex downTo 0) {
                var left = leftZeros[index]

                if (s[index] == '0') {
                    left = index - left
                }

                result += left * right[(s[index] - '0') xor 1]

                right[s[index] - '0']++
            }

            return result
        }
    }

    expect {
        Solution().numberOfWays(
            "0001100100"
        )
    }
}