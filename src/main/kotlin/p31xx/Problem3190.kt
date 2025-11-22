package p31xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minimumOperations(nums: IntArray): Int {
            return nums.sumOf { (it % 3).sign }
        }
    }

    expect {
        Solution().minimumOperations(
            intArrayOf()
        )
    }
}
