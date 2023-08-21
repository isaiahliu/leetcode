package p02xx

import util.expect

fun main() {
    class Solution {
        fun addDigits(num: Int): Int {
            if (num < 10) {
                return num
            }

            var t = num
            var sum = 0
            while (t > 0) {
                sum += t % 10
                t /= 10
            }

            return addDigits(sum)
        }
    }

    expect {
        Solution().addDigits(
            1
        )
    }
}

