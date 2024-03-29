package p17xx

import util.expect

fun main() {
    class Solution {
        fun maximumBinaryString(binary: String): String {
            var firstOne = 0
            var zero = 0
            var lastOne = 0

            binary.forEach {
                when {
                    it == '0' -> zero++
                    it == '1' && zero == 0 -> {
                        firstOne++
                    }

                    else -> lastOne++
                }
            }

            return "${"1".repeat(firstOne + (zero - 1).coerceAtLeast(0))}${"0".repeat(zero.coerceAtMost(1))}${
                "1".repeat(lastOne)
            }"
        }
    }

    expect {
        Solution().maximumBinaryString(
            ""
        )
    }
}
