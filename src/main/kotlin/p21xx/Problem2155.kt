package p21xx

import util.expect

fun main() {
    class Solution {
        fun maxScoreIndices(nums: IntArray): List<Int> {
            val sums = Array(nums.size + 1) {
                intArrayOf(0, 0)
            }

            var zeroCount = 0
            var oneCount = 0
            for (i in nums.indices) {
                if (nums[i] == 0) {
                    zeroCount++
                    sums[i + 1][0] = zeroCount
                }

                if (nums[nums.lastIndex - i] == 1) {
                    oneCount++
                    sums[nums.lastIndex - i][1] = oneCount
                }
            }

            val result = arrayListOf<Int>()
            var max = -1
            sums.forEachIndexed { index, scores ->
                scores.sum().also {
                    if (it > max) {
                        max = it
                        result.clear()
                    }

                    if (it == max) {
                        result.add(index)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxScoreIndices(
            intArrayOf(0, 0, 1, 0)
        )
    }
}