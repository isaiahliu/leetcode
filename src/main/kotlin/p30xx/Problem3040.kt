package p30xx

import util.expect

fun main() {
    class Solution {
        fun maxOperations(nums: IntArray): Int {
            return setOf(
                nums[0] + nums[1],
                nums[0] + nums[nums.lastIndex],
                nums[nums.lastIndex] + nums[nums.lastIndex - 1]
            ).maxOf { target ->
                var result = 0

                val cache = Array(nums.size) {
                    IntArray(nums.size) { -1 }
                }

                fun dfs(l: Int, r: Int, size: Int) {
                    result = maxOf(result, size)

                    if (l < r) {
                        if (cache[l][r] < size) {
                            cache[l][r] = size
                        } else {
                            return
                        }
                        if (nums[l] + nums[l + 1] == target) {
                            dfs(l + 2, r, size + 1)
                        }

                        if (nums[l] + nums[r] == target) {
                            dfs(l + 1, r - 1, size + 1)
                        }

                        if (nums[r] + nums[r - 1] == target) {
                            dfs(l, r - 2, size + 1)
                        }
                    }
                }

                dfs(0, nums.lastIndex, 0)

                result
            }
        }
    }

    expect {
        Solution().maxOperations(
            intArrayOf()
        )
    }
}
