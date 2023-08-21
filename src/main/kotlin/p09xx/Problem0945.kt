package p09xx

import util.expect

fun main() {
class Solution {
    fun minIncrementForUnique(nums: IntArray): Int {
        nums.sort()

        var result = 0

        var target = nums[0]
        nums.forEach {
            if (it < target) {
                result += target - it
            } else {
                target = it
            }

            target++
        }

        return result
    }
}


    expect {
        Solution().minIncrementForUnique(
            intArrayOf(3, 2, 1, 2, 1, 7)
        )
    }
}
