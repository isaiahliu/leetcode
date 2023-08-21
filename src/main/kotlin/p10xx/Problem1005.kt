package p10xx

import util.expect

fun main() {
    class Solution {
        fun largestSumAfterKNegations(nums: IntArray, k: Int): Int {
            var remaining = k
            var result = 0

            var min = Int.MAX_VALUE

            nums.sorted().forEach {
                result += when {
                    remaining == 0 -> it
                    it < 0 -> {
                        remaining--
                        min = -it
                        -it
                    }

                    remaining % 2 == 0 -> {
                        remaining = 0
                        it
                    }

                    it > min -> {
                        remaining = 0
                        it - min * 2
                    }

                    else -> {
                        remaining = 0
                        -it
                    }
                }
            }

            if (remaining % 2 == 1) {
                result -= min * 2
            }

            return result
        }
    }

    expect {
        Solution().largestSumAfterKNegations(
            intArrayOf(-4, -2, -3), 4
        )
    }
}
