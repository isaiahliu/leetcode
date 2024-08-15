package p31xx

import util.expect

fun main() {
    class Solution {
        fun minimumValueSum(nums: IntArray, andValues: IntArray): Int {
            val INF = (1 shl 20) - 1
            val n = nums.size
            val m = andValues.size
            val cache: Array<HashMap<Int, Int>> = Array(m * n) { hashMapOf() }

            fun dfs(i: Int, j: Int, c: Int, nums: IntArray, andValues: IntArray): Int {
                var cur = c
                val key = i * m + j
                if (i == nums.size && j == andValues.size) {
                    return 0
                }
                if (i == nums.size || j == andValues.size) {
                    return INF
                }
                cache[key][cur]?.also {
                    return it
                }

                cur = cur and nums[i]
                if ((cur and andValues[j]) < andValues[j]) {
                    return INF
                }
                var res = dfs(i + 1, j, cur, nums, andValues)
                if (cur == andValues[j]) {
                    res = minOf(res, (dfs(i + 1, j + 1, INF, nums, andValues) + nums[i]))
                }
                cache[key][cur] = res
                return res
            }

            val result = dfs(0, 0, INF, nums, andValues)
            return if (result < INF) result else -1
        }
    }

    expect {
        Solution().minimumValueSum(
            intArrayOf(), intArrayOf()
        )
    }
}
