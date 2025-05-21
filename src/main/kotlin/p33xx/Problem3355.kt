package p33xx

import util.expect

fun main() {
    class Solution {
        fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
            val diff = IntArray(nums.size + 1)

            queries.forEach { (from, to) ->
                diff[from] += 1
                diff[to + 1] -= 1
            }

            nums.indices.forEach {
                if (diff[it] < nums[it]) {
                    return false
                }

                diff[it + 1] += diff[it]
            }

            return true
        }
    }

    expect {
        Solution().isZeroArray(
            intArrayOf(), arrayOf()
        )
    }
}
