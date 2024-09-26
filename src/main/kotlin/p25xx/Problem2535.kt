package p25xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun differenceOfSum(nums: IntArray): Int {
            return nums.sumOf {
                var result = it
                var t = it
                while (t > 0) {
                    result -= t % 10
                    t /= 10
                }
                result
            }.absoluteValue
        }
    }

    expect {
        Solution().differenceOfSum(
            intArrayOf()
        )
    }
}

