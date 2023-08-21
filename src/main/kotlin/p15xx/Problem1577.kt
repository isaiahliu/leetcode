package p15xx

import util.expect

fun main() {
    class Solution {
        fun numTriplets(nums1: IntArray, nums2: IntArray): Int {
            fun find(left: IntArray, right: IntArray): Int {
                val leftNums = hashMapOf<Long, Int>()

                left.forEach {
                    leftNums[it.toLong() * it] = (leftNums[it.toLong() * it] ?: 0) + 1
                }

                val rightNums = hashMapOf<Long, Int>()

                var result = 0
                right.forEach {
                    leftNums.forEach { n, count ->
                        if (n % it == 0L) {
                            rightNums[n / it]?.also {
                                result += count * it
                            }
                        }
                    }

                    rightNums[it.toLong()] = (rightNums[it.toLong()] ?: 0) + 1
                }

                return result
            }

            return find(nums1, nums2) + find(nums2, nums1)
        }
    }

    expect {
        Solution().numTriplets(
            intArrayOf(), intArrayOf()
        )
    }
}

