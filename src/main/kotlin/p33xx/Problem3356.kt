package p33xx

import util.expect

fun main() {
    class Solution {
        fun minZeroArray(nums: IntArray, queries: Array<IntArray>): Int {
            fun tryFirst(count: Int): Boolean {
                val diff = IntArray(nums.size + 1)

                queries.take(count).forEach { (from, to, value) ->
                    diff[from] += value
                    diff[to + 1] -= value
                }

                nums.indices.forEach {
                    if (diff[it] < nums[it]) {
                        return false
                    }

                    diff[it + 1] += diff[it]
                }

                return true
            }

            var l = 0
            var r = queries.size

            var result = Int.MAX_VALUE

            while (l <= r) {
                val m = l + (r - l) / 2

                if (tryFirst(m)) {
                    result = m
                    r = m - 1
                } else {
                    l = m + 1
                }
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    expect {
        Solution().minZeroArray(
            intArrayOf(5), arrayOf(intArrayOf(0, 0, 5))
        )
    }
}
