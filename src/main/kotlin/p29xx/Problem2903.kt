package p29xx

import util.expect
import kotlin.math.abs

fun main() {
    class Solution {
        fun findIndices(nums: IntArray, indexDifference: Int, valueDifference: Int): IntArray {
            for (i in nums.indices) {
                for (j in i + indexDifference until nums.size) {
                    if (abs(nums[i] - nums[j]) >= valueDifference) {
                        return intArrayOf(i, j)
                    }
                }
            }

            return intArrayOf(-1, -1)
        }
    }

    expect {
        Solution().findIndices(
            intArrayOf(7, 10, 2), 1, 2
        )
    }
}
