package p23xx

import util.expect

fun main() {
    class Solution {
        fun validPartition(nums: IntArray): Boolean {
            val visited = hashSetOf<Int>()
            fun dfs(index: Int): Boolean {
                return when {
                    index == nums.size -> {
                        return true
                    }

                    !visited.add(index) -> {
                        return false
                    }

                    else -> {
                        var result = false
                        if (nums.size - index >= 2) {
                            if (nums[index] == nums[index + 1]) {
                                result = dfs(index + 2)
                            }
                        }

                        if (!result && nums.size - index >= 3) {
                            if (nums[index] == nums[index + 1] && nums[index] == nums[index + 2] || nums[index] + 1 == nums[index + 1] && nums[index] + 2 == nums[index + 2]) {
                                result = dfs(index + 3)
                            }
                        }

                        result
                    }
                }
            }

            return dfs(0)
        }
    }

    expect {
        Solution().validPartition(
            intArrayOf(
                1699,
                1700,
                1701,
                1701,
                1701,
            )
        )
    }
}