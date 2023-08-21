package p07xx

import util.expect

fun main() {
    class Solution {
        fun numSubarrayBoundedMax(nums: IntArray, left: Int, right: Int): Int {
            var result = 0

            var size = 0
            var lastMatchIndex: Int? = null
            nums.forEachIndexed { index, num ->
                when {
                    num > right -> {
                        size = 0
                        lastMatchIndex = null
                    }

                    num < left -> {
                        size++

                        lastMatchIndex?.also {
                            result += size - (index - it)

                        }
                    }

                    else -> {
                        size++

                        result += size
                        lastMatchIndex = index
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().numSubarrayBoundedMax(
            intArrayOf(), 1, 1
        )
    }
}