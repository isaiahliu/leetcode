package p21xx

import util.expect

fun main() {
    class Solution {
        fun getDescentPeriods(prices: IntArray): Long {
            var size = 0
            var pre = prices[0]

            var result = 0L
            prices.forEach {
                if (it + size == pre) {
                    size++
                } else {
                    pre = it
                    size = 1
                }
                result += size
            }

            return result
        }
    }

    expect {
        Solution().getDescentPeriods(
            intArrayOf()
        )
    }
}