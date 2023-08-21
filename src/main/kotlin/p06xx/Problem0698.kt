package p06xx

import util.expect

fun main() {
    class Solution {
        fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
            if (k == 1) {
                return true
            }

            val sum = nums.sum()
            if (sum % k != 0) {
                return false
            }

            val avg = sum / k
            if (nums.any { it > avg }) {
                return false
            }

            val dp = Array(k) {
                hashSetOf<Int>()
            }

            for (i in 1 until (1 shl nums.size)) {
                val num = nums.indices.map {
                    if (i and (1 shl it) > 0) {
                        nums[it]
                    } else {
                        0
                    }
                }.sum()

                when {
                    num == avg -> {
                        dp[1].add(i)
                    }

                    num % avg == 0 -> {
                        loop@ for (left in 1 until dp.size) {
                            val right = num / avg - left

                            if (left > right) {
                                break
                            }

                            for (t in dp[left]) {
                                if (t and i == t && i - t in dp[right]) {
                                    (dp.getOrNull(left + right) ?: return true).add(i)
                                    break@loop
                                }
                            }
                        }
                    }
                }
            }
            return false
        }
    }

    expect {
        Solution().canPartitionKSubsets(
            intArrayOf(1, 1, 1, 1, 2, 2, 2, 2), 2
        )
    }
}