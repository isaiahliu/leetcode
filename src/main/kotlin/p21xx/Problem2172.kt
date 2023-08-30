package p21xx

import util.expect

fun main() {
    class Solution {
        fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
            val mask = numSlots.takeHighestOneBit() * 2 - 1

            nums.forEachIndexed { index, num ->
                nums[index] = num and mask
            }

            nums.sort()

            val dp = hashMapOf(0 to 0)

            for (slot in 1..numSlots) {
                val lastDp = dp.toMap()

                lastDp.forEach { (status, value) ->
                    nums.forEachIndexed { index, num ->
                        if (nums.getOrNull(index - 1) == num && status and (1 shl (index - 1)) == 0) {
                            return@forEachIndexed
                        }

                        val pos = 1 shl index

                        if (status and pos == 0) {
                            val newValue = value + (slot and num)
                            dp[status + pos] = dp[status + pos]?.takeIf { it > newValue } ?: newValue
                        }

                        for (index2 in index + 1 until nums.size) {
                            if (index2 > index + 1 && nums.getOrNull(index2 - 1) == nums[index2] && status and (1 shl (index2 - 1)) == 0) {
                                continue
                            }

                            val pos2 = 1 shl index2

                            if (status and pos == 0 && status and pos2 == 0) {
                                val newValue = value + (slot and num) + (slot and nums[index2])
                                dp[status + pos + pos2] =
                                    dp[status + pos + pos2]?.takeIf { it > newValue } ?: newValue
                            }
                        }
                    }
                }
            }

            return dp[(1 shl nums.size) - 1] ?: 0
        }
    }

    expect {
        Solution().maximumANDSum(
            intArrayOf(4, 14, 6, 9, 3, 6, 12, 15, 4, 12, 13, 15, 7, 3, 3, 3, 13, 14), 9
        )
    }
}