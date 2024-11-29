package p32xx

import util.expect

fun main() {
    class Solution {
        fun canAliceWin(nums: IntArray): Boolean {
            val sums = IntArray(3)
            nums.forEach {
                when {
                    it < 10 -> sums[0] += it
                    it < 100 -> sums[1] += it
                    else -> sums[2] += it
                }
            }

            return sums[0] > sums[1] + sums[2] || sums[1] > sums[0] + sums[2]
        }
    }

    expect {
        Solution().canAliceWin(
            intArrayOf()
        )
    }
}
