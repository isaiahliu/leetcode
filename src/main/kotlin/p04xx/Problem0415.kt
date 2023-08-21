package p04xx

import util.expect

fun main() {
    class Solution {
        fun addStrings(num1: String, num2: String): String {
            val result = IntArray(num1.length.coerceAtLeast(num2.length) + 1)

            var sum = 0
            for (i in result.indices) {
                val left = num1.getOrNull(num1.length - i - 1)?.let { it - '0' } ?: 0
                val right = num2.getOrNull(num2.length - i - 1)?.let { it - '0' } ?: 0

                sum += left + right

                result[result.size - i - 1] = sum % 10
                sum /= 10
            }

            return result.joinToString("") { it.toString() }.trimStart('0').ifEmpty { "0" }
        }
    }

    expect {
        Solution().addStrings(
            "11", "123"
        )
    }
}


