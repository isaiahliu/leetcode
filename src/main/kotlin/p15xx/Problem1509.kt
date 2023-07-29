package p15xx

import util.Ints
import util.get
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDifference(nums: IntArray): Int {
            if (nums.size <= 4) {
                return 0
            }
            nums.sort()

            return (nums[nums.lastIndex] - nums[3])
                .coerceAtMost(nums[nums.lastIndex - 1] - nums[2])
                .coerceAtMost(nums[nums.lastIndex - 2] - nums[1])
                .coerceAtMost(nums[nums.lastIndex - 3] - nums[0])
        }
    }

    measureTimeMillis {
        Solution().minDifference(
            Ints[1, 2, 3, 4, 5, 6, 7, 8, 9]
        ).also { println(it) }
    }
}

