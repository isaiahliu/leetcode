package p10xx

import util.expect

fun main() {
    class Solution {
        fun bitwiseComplement(n: Int): Int {
            return n.toString(2).map {
                '1' - (it - '0')
            }.toCharArray().let { String(it) }.toInt(2)
        }
    }

    expect {
        Solution().bitwiseComplement(
            12
        )
    }
}
