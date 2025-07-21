package p25xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums1: IntArray, nums2: IntArray, k: Int): Long {
            var result = 0L
            var balance = 0L

            nums1.indices.forEach {
                val diff = nums1[it] - nums2[it]

                when {
                    diff == 0 -> {}

                    k == 0 || diff % k != 0 -> {
                        return -1
                    }

                    diff > 0 -> {
                        result += diff / k
                        balance += diff
                    }

                    else -> {
                        balance += diff
                    }

                }
            }

            return result.takeIf { balance == 0L } ?: -1
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(), intArrayOf(), 1
        )
    }
}

