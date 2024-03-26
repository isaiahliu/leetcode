package p25xx

import util.expect
import java.math.BigInteger

fun main() {
    class Solution {
        fun countWays(ranges: Array<IntArray>): Int {
            ranges.sortWith(compareBy { it[0] })

            var size = BigInteger.ONE

            var end = ranges[0][1]
            ranges.forEach { (from, to) ->
                if (from > end) {
                    size++
                }

                end = maxOf(end, to)
            }

            return 2.toBigInteger().modPow(size, 1000000007.toBigInteger()).toInt()
        }
    }

    expect {
        Solution().countWays(
            arrayOf()
        )
    }
}