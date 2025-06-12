package p34xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maxAdjacentDistance(nums: IntArray): Int {
            return nums.indices.maxOf {
                (nums[it] - nums[(it + 1) % nums.size]).absoluteValue
            }
        }
    }

    expect {
        Solution().maxAdjacentDistance(
            intArrayOf()
        )
    }
}
