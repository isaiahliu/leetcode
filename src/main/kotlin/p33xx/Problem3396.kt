package p33xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minimumOperations(nums: IntArray): Int {
            var result = nums.size / 3 + (nums.size % 3).sign
            val set = hashSetOf<Int>()
            for (index in nums.lastIndex downTo 0) {
                if (!set.add(nums[index])) {
                    break
                }

                if (index % 3 == 0) {
                    result--
                }
            }

            return result
        }
    }
    
    expect {
        Solution().minimumOperations(
            intArrayOf()
        )
    }
}
