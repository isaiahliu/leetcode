package p31xx

import util.expect

fun main() {
    class Solution {
        fun sumDigitDifferences(nums: IntArray): Long {
            val counts = Array(nums[0].toString().length) {
                hashMapOf<Char, Int>()
            }

            nums.forEach {
                it.toString().forEachIndexed { index, c ->
                    counts[index].also {
                        it[c] = (it[c] ?: 0) + 1
                    }
                }
            }

            return nums.size.toLong() * (nums.size - 1) / 2 * counts.size - counts.sumOf {
                it.values.sumOf {
                    it.toLong() * (it - 1) / 2
                }
            }
        }
    }

    expect {
        Solution().sumDigitDifferences(
            intArrayOf()
        )
    }
}
