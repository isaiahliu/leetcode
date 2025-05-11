package p29xx

import util.expect

fun main() {
    class Solution {
        fun minSum(nums1: IntArray, nums2: IntArray): Long {
            val sums = LongArray(2)
            val hasZeros = BooleanArray(2)

            nums1.forEach {
                if (it == 0) {
                    hasZeros[0] = true
                    sums[0]++
                } else {
                    sums[0] += it
                }
            }
            nums2.forEach {
                if (it == 0) {
                    hasZeros[1] = true
                    sums[1]++
                } else {
                    sums[1] += it
                }
            }

            if (sums[0] > sums[1] && !hasZeros[1] || sums[0] < sums[1] && !hasZeros[0]) {
                return -1
            }

            return sums.max()
        }
    }

    expect {
        Solution().minSum(
            intArrayOf(7, 10, 2), intArrayOf()
        )
    }
}
