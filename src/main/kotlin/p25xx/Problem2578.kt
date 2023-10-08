package p25xx

import util.expect

fun main() {
    class Solution {
        fun splitNum(num: Int): Int {
            val result = intArrayOf(0, 0)
            var index = 0

            num.toString().toCharArray().sorted().forEach {
                result[index] *= 10
                result[index] += it - '0'

                index = 1 - index
            }

            return result.sum()
        }
    }

    expect {
        Solution().splitNum(
            5
        )
    }
}

