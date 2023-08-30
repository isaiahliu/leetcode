package p21xx

import util.expect

fun main() {
    class Solution {
        fun sortJumbled(mapping: IntArray, nums: IntArray): IntArray {
            val mapped = nums.map {
                var r = mapping[it % 10]
                var base = 10

                var t = it / 10
                while (t > 0) {
                    r += mapping[t % 10] * base

                    t /= 10
                    base *= 10
                }

                r
            }

            return mapped.indices.sortedBy { mapped[it] }.map { nums[it] }.toIntArray()
        }
    }

    expect {
        Solution().sortJumbled(
            intArrayOf(8, 9, 4, 0, 2, 1, 3, 5, 7, 6), intArrayOf(991, 338, 38)
        ).toList()
    }
}