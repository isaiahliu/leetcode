package p19xx

import util.expect

fun main() {
    class Solution {
        fun countQuadruplets(nums: IntArray): Int {
            val numCounts = Array(4) { hashMapOf<Int, Int>() }

            numCounts[0][0] = 1

            var result = 0
            nums.forEach { num ->
                for (cIndex in 3 downTo 1) {
                    numCounts[cIndex - 1].forEach { (sum, count) ->
                        numCounts[cIndex][sum + num] = (numCounts[cIndex][sum + num] ?: 0) + count
                    }
                }

                numCounts[3][num]?.also {
                    result += it
                }
            }

            return result
        }
    }

    expect {
        Solution().countQuadruplets(
            intArrayOf(28, 8, 49, 85, 37, 90, 20, 8)
        )
    }
}