package p25xx

import util.expect

fun main() {
    class Solution {
        fun separateDigits(nums: IntArray): IntArray {
            val digits = arrayListOf<Int>()

            for (i in nums.lastIndex downTo 0) {
                var t = nums[i]

                while (t > 0) {
                    digits.add(t % 10)
                    t /= 10
                }
            }

            return digits.reversed().toIntArray()
        }
    }

    expect {
        Solution().separateDigits(
            intArrayOf(1, 3, 2, 4, 5)
        )
    }
}