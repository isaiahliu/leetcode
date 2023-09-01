package p22xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun findClosestNumber(nums: IntArray): Int {
            return nums.minWith(compareBy<Int> { it.absoluteValue }.thenByDescending { it })
        }
    }

    expect {
        Solution().findClosestNumber(
            intArrayOf()
        )
    }
}