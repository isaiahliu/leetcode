package p00xx

import util.expect

fun main() {
    class Solution {
        fun sortColors(nums: IntArray): Unit {
            var leftIndex = 0
            var rightIndex = nums.lastIndex

            var cursor = 0
            while (cursor <= rightIndex) {
                when (nums[cursor]) {
                    0 -> {
                        val t = nums[leftIndex]
                        nums[cursor] = t
                        nums[leftIndex] = 0

                        leftIndex++

                        if (cursor < leftIndex) {
                            cursor = leftIndex
                        }
                    }

                    2 -> {
                        val t = nums[rightIndex]
                        nums[cursor] = t
                        nums[rightIndex] = 2

                        rightIndex--
                    }

                    else -> {
                        cursor++
                    }
                }
            }
        }
    }

    expect {
        Solution().sortColors(intArrayOf(0, 2, 1))
    }
}

