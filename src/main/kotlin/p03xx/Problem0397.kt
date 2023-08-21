package p03xx

import util.expect

fun main() {
    class Solution {
        val cache = hashMapOf<Int, Int>()

        fun integerReplacement(n: Int): Int {
            if (n == 1) {
                return 0
            }

            if (n in cache) {
                return cache[n] ?: 0
            }

            var t = n
            var result = 0
            while (t % 2 == 0) {
                t /= 2
                result++
            }

            if (t > 1) {
                result += integerReplacement(t + 1).coerceAtMost(integerReplacement(t - 1)) + 1
            }

            cache[n] = result
            return result
        }
    }

    expect {
        Solution().integerReplacement(
            7
        )
    }
}


