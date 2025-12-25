package p35xx

import util.expect

fun main() {
    class Solution {
        fun countPermutations(complexity: IntArray): Int {
            val m = 1000000007
            var result = 1L

            for (i in 1 until complexity.size) {
                if (complexity[i] <= complexity[0]) {
                    return 0
                }

                result *= i
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countPermutations(
            intArrayOf()
        )
    }
}
