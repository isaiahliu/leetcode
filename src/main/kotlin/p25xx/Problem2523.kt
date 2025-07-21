package p25xx

import util.expect

fun main() {
    class Solution {
        val factors = arrayListOf<Int>()
        val np = BooleanArray(1100000)

        var lastFactor = 1
        var lastNumber = 2
        fun calculateMore(maxNumber: Int) {
            while (lastFactor < maxNumber) {
                if (!np[lastNumber]) {
                    lastFactor = lastNumber
                    factors += lastNumber

                    var t = lastNumber * 2
                    while (t < np.size) {
                        np[t] = true
                        t += lastNumber
                    }
                }

                lastNumber++
            }
        }

        fun closestPrimes(left: Int, right: Int): IntArray {
            calculateMore(right)

            var lowerIndex = -1
            var upperIndex = -1

            var l = 0
            var r = factors.lastIndex
            while (l <= r) {
                val m = (l + r) / 2
                if (factors[m] >= left) {
                    lowerIndex = m
                    r = m - 1
                } else {
                    l = m + 1
                }
            }
            l = 0
            r = factors.lastIndex
            while (l <= r) {
                val m = (l + r) / 2
                if (factors[m] <= right) {
                    upperIndex = m
                    l = m + 1
                } else {
                    r = m - 1
                }
            }

            var minDiff = Int.MAX_VALUE
            val result = intArrayOf(-1, -1)

            for (index in lowerIndex until upperIndex) {
                val diff = factors[index + 1] - factors[index]

                if (diff < minDiff) {
                    minDiff = diff
                    result[0] = factors[index]
                    result[1] = factors[index + 1]
                }
            }

            return result
        }
    }

    expect {
        Solution().closestPrimes(
            10, 19
        )
    }
}