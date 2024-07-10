package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun incremovableSubarrayCount(nums: IntArray): Long {
            val rightIncs = TreeMap<Int, Int>()
            rightIncs[Int.MAX_VALUE] = 1

            var pre = Int.MAX_VALUE
            for (i in nums.lastIndex downTo 0) {
                val num = nums[i]

                if (num < pre) {
                    rightIncs[num] = rightIncs.size + 1
                    pre = num
                } else {
                    break
                }
            }

            var result = 0L

            pre = -1
            for ((index, num) in nums.withIndex()) {
                result += rightIncs.higherEntry(pre)?.value ?: 0

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
