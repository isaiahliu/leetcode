package p21xx

import util.expect

fun main() {
    class Solution {
        fun countElements(nums: IntArray): Int {
            val min = nums.min()
            val max = nums.max()

            return if (min == max) {
                0
            } else {
                nums.size - nums.count { it == min } - nums.count { it == max }
            }
        }
    }

    expect {
        Solution().countElements(
            intArrayOf()
        )
    }
}