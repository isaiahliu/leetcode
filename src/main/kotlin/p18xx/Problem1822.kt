package p18xx

import util.expect

fun main() {
    class Solution {
        fun arraySign(nums: IntArray): Int {
            var sign = 1
            nums.forEach {
                when {
                    it < 0 -> {
                        sign = -sign
                    }

                    it == 0 -> {
                        return 0
                    }
                }
            }

            return sign
        }
    }
    expect {
        Solution().arraySign(
            intArrayOf(1)
        )
    }
}
