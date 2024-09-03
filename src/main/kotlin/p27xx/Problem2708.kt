package p27xx

import util.expect

fun main() {
    class Solution {
        fun maxStrength(nums: IntArray): Long {
            var result = 1L
            var maxNeg = Int.MIN_VALUE
            var noneZeroCount = nums.size
            nums.forEach {
                when {
                    it > 0 -> {
                        result *= it
                    }

                    it < 0 -> {
                        result *= it
                        maxNeg = maxOf(maxNeg, it)
                    }

                    else -> {
                        noneZeroCount--
                    }
                }
            }

            return when {
                nums.size == 1 -> nums[0].toLong()
                noneZeroCount == 0 -> 0L
                noneZeroCount == 1 -> maxOf(result, 0)
                result < 0 -> result / maxNeg
                else -> result
            }
        }
    }

    expect {
        Solution().maxStrength(
            intArrayOf()
        )
    }
}
