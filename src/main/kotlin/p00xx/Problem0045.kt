package p00xx

import util.expect

fun main() {
    class Solution {
        fun jump(nums: IntArray): Int {
            var steps = 0
            var startIndex = 0
            var endIndex = 0

            while (endIndex < nums.lastIndex) {
                val nextStart = endIndex + 1
                for (i in startIndex..endIndex) {
                    endIndex = endIndex.coerceAtLeast(i + nums[i])
                }

                if (endIndex < nextStart) {
                    return 0
                }

                startIndex = nextStart
                steps++
            }

            return steps
        }
    }

    expect {
        Solution().jump(intArrayOf(1))
    }
}


