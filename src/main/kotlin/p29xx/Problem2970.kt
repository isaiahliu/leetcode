package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun incremovableSubarrayCount(nums: IntArray): Int {
            val rightIncs = TreeSet<Int>()
            rightIncs += Int.MAX_VALUE

            for (i in nums.lastIndex downTo 0) {
                val num = nums[i]

                if (num < (rightIncs.firstOrNull() ?: Int.MAX_VALUE)) {
                    rightIncs.add(num)
                } else {
                    break
                }
            }

            var result = 0

            var pre = -1
            for ((index, num) in nums.withIndex()) {
                result += rightIncs.tailSet(pre, false).size

                if (num > pre) {
                    if (index + rightIncs.size > nums.size) {
                        result--
                    }
                    pre = num
                } else {
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().incremovableSubarrayCount(
            intArrayOf(4, 3, 7, 5)
        )
    }
}
