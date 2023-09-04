package p22xx

import util.expect

fun main() {
    class Solution {
        fun removeDigit(number: String, digit: Char): String {
            var lastIndex = 0

            for (index in number.indices) {
                if (number[index] == digit) {
                    if (number.getOrNull(index + 1)?.takeIf { it > digit } != null) {
                        return number.take(index) + number.drop(index + 1)
                    } else {
                        lastIndex = index
                    }
                }
            }

            return number.take(lastIndex) + number.drop(lastIndex + 1)
        }
    }

    expect {
        Solution().removeDigit(
            "123", '3'
        )
    }
}