package p07xx

import util.expect

fun main() {
    class Solution {
        fun dominantIndex(nums: IntArray): Int {
            var maxIndex = 0
            var max = Int.MIN_VALUE
            var second = Int.MIN_VALUE

            nums.forEachIndexed { index, num ->
                if (num > max) {
                    second = max
                    max = num
                    maxIndex = index
                } else if (num > second) {
                    second = num
                }
            }

            return maxIndex.takeIf { max >= second * 2 } ?: -1
        }
    }

    expect {
        Solution().dominantIndex(
            intArrayOf(1, 2, 3, 4)
        )
    }
}