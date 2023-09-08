package p23xx

import util.expect

fun main() {
    class Solution {
        fun minimumReplacement(nums: IntArray): Long {
            var result = 0L

            var max = nums.last()
            for (index in nums.lastIndex - 1 downTo 0) {
                val num = nums[index]
                if (num <= max) {
                    max = num
                } else if (num % max == 0) {
                    result += num / max - 1
                } else {
                    var rem = num % max
                    val fullCount = num / max
                    var remainNum = max

                    while (rem < remainNum) {
                        rem += fullCount
                        remainNum--
                    }

                    result += fullCount
                    max = remainNum
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumReplacement(
            intArrayOf(3, 9, 3)
        )
    }
}