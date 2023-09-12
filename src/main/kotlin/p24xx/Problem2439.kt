package p24xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minimizeArrayValue(nums: IntArray): Int {
            var result = 0

            var sum = 0L

            nums.forEachIndexed { index, num ->
                sum += num

                val min = sum / (index + 1) + (sum % (index + 1)).sign

                result = result.coerceAtLeast(min.toInt())
            }

            return result
        }
    }

    expect {
        Solution().minimizeArrayValue(
            intArrayOf()
        )
    }
}