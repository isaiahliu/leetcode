package p04xx

import util.expect

fun main() {
    class Solution {
        fun findNthDigit(n: Int): Int {
            var digitCount = 1
            var start = 1L
            var end = 9L

            var t = n.toLong()
            while (t > (end - start + 1) * digitCount) {
                t -= (end - start + 1) * digitCount
                digitCount += 1
                start *= 10
                end *= 10
                end += 9
            }

            val targetNum = start + (t - 1) / digitCount

            return targetNum.toString().let {
                it[(t.toInt() - 1) % digitCount]
            } - '0'
        }
    }

    expect {
        Solution().findNthDigit(
            Int.MAX_VALUE
        )
    }
}


