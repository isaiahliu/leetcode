package p22xx

import util.expect

fun main() {
    class Solution {
        fun divideArray(nums: IntArray): Boolean {
            return nums.toList().groupingBy { it }.eachCount().values.all { it % 2 == 0 }
        }
    }

    expect {
        Solution().divideArray(
            intArrayOf(4, 2, 20)
        )
    }
}