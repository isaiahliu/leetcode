package p25xx

import util.expect

fun main() {
    class Solution {
        fun monkeyMove(n: Int): Int {
            return (2.toBigInteger().modPow(n.toBigInteger(), 1000000007.toBigInteger()) - 2.toBigInteger()).mod(1000000007.toBigInteger()).toInt()
        }
    }

    expect {
        Solution().monkeyMove(
            3
        )
    }
}