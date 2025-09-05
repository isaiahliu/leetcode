package p27xx

import util.expect

fun main() {
    class Solution {
        fun makeTheIntegerZero(num1: Int, num2: Int): Int {
            var result = 1
            while (true) {
                val num = num1 - num2.toLong() * result
                when {
                    num < result -> {
                        return -1
                    }

                    result >= num.countOneBits() -> {
                        return result
                    }

                    else -> result++
                }
            }
        }
    }

    expect {
        Solution().makeTheIntegerZero(
            5, 7
        )
    }
}
