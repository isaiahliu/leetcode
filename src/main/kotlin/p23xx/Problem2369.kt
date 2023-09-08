package p23xx

import util.expect

fun main() {
    class Solution {
        fun validPartition(nums: IntArray): Boolean {
            val SAME_START = 0
            val SAME_END = 1
            val LEFT = 2
            val MID = 3
            val RIGHT = 4

            val visited = hashSetOf<Pair<Int, Int>>()
            fun dfs(index: Int, status: Int): Boolean {
                return when {
                    index == nums.size -> {
                        return status == SAME_START || status == LEFT
                    }

                    !visited.add(index to status) -> {
                        return false
                    }

                    status == SAME_START -> {
                        dfs(index + 1, SAME_END)
                    }

                    status == SAME_END -> {
                        if (nums[index - 1] == nums[index]) {
                            dfs(index + 1, SAME_START) || dfs(index + 1, SAME_END) || dfs(index + 1, LEFT)
                        } else {
                            false
                        }
                    }

                    status == LEFT -> {
                        dfs(index + 1, MID)
                    }

                    status == MID -> {
                        if (nums[index - 1] + 1 == nums[index]) {
                            dfs(index + 1, RIGHT)
                        } else {
                            false
                        }
                    }

                    else -> {
                        if (nums[index - 1] + 1 == nums[index]) {
                            dfs(index + 1, SAME_START) || dfs(index + 1, LEFT)
                        } else {
                            false
                        }
                    }
                }
            }

            return dfs(1, SAME_END) || dfs(1, MID)
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