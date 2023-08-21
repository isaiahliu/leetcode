package p18xx

import util.expect

fun main() {
    class Solution {
        fun countNicePairs(nums: IntArray): Int {
            val counts = hashMapOf<Int, Int>()

            val m = 1000000007
            var result = 0L

            fun Int.revDiff(): Int {
                return this - this.toString().reversed().toInt()
            }

            nums.forEach {
                it.revDiff().also {
                    counts[it]?.also {
                        result += it
                        result %= m
                    }

                    counts[it] = (counts[it] ?: 0) + 1
                }
            }


            return result.toInt()
        }
    }

    expect {
        Solution().countNicePairs(
            intArrayOf()
        )
    }
}
