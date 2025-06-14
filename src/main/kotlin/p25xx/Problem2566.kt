package p25xx

import util.expect

fun main() {
    class Solution {
        fun minMaxDifference(num: Int): Int {
            val diffs = IntArray(10)

            var t = num
            var b = 1

            var maxDigit = 9
            var minDigit = 1
            do {
                (t % 10).also {
                    diffs[it] += b

                    if (it < 9) {
                        maxDigit = it
                    }

                    if (it > 0) {
                        minDigit = it
                    }
                }

                b *= 10
                t /= 10
            } while (t > 0)

            return (9 - maxDigit) * diffs[maxDigit] + minDigit * diffs[minDigit]
        }
    }

    expect {
        Solution().minMaxDifference(
            11891
        )
    }
}