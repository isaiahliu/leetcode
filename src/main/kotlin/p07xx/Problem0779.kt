package p07xx

import util.expect

fun main() {
    class Solution {
        fun kthGrammar(n: Int, k: Int): Int {
            var t = k - 1

            var result = 0
            repeat(n - 1) {
                result = t % 2 xor result
                t /= 2
            }

            return result
        }
    }

    expect {
        Solution().kthGrammar(
            3, 2
        )
    }
}