package p27xx

import util.expect
import kotlin.math.max

fun main() {
    class Solution {
        fun longestAlternatingSubarray(nums: IntArray, threshold: Int): Int {
            var result = 0
            var current = 0
            var rem = 0
            nums.forEach {
                when {
                    it > threshold -> {
                        current = 0
                    }

                    else -> {
                        when {
                            current == 0 || it % 2 == rem -> {
                                current = (it % 2) xor 1
                            }

                            else -> current++
                        }
                        result = max(result, current)
                        rem = it % 2
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().longestAlternatingSubarray(
            intArrayOf(), 3
        )
    }
}
