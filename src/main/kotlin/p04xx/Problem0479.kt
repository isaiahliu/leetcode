package p04xx

import util.expect

fun main() {
    class Solution {
        fun largestPalindrome(n: Int): Int {
            fun Long.palindrome(): Boolean {
                return this.toString().takeIf { it == it.reversed() } != null
            }

            fun Long.next(): Long? {
                if (this <= 1) {
                    return null
                }
                return when (this % 10) {
                    9L -> this - 2
                    7L -> this - 4
                    3L -> this - 2
                    1L -> this - 2
                    else -> null
                }
            }

            val max = "9".repeat(n).toLong()
            val min = ("9".repeat(n - 1).toLongOrNull() ?: 0L) + 1

            var left = max + 2

            var result = 0L
            while (true) {
                left = left.next() ?: break

                if (left * left < result) {
                    break
                }

                var right = left
                while (true) {
                    val prod = left * right

                    if (prod <= result) {
                        break
                    }

                    if (prod.palindrome()) {
                        result = prod
                        break
                    }

                    right = right.next() ?: break
                }
            }

            return (result % 1337).toInt()
        }
    }

    expect {
        Solution().largestPalindrome(
            4
        )
    }
}