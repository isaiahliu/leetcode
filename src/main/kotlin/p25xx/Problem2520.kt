package p25xx

import util.expect

fun main() {
    class Solution {
        fun countDigits(num: Int): Int {
            return num.toString().count {
                it > '0' && num % (it - '0') == 0
            }
        }
    }

    expect {
        Solution().countDigits(
            39
        )
    }
}
