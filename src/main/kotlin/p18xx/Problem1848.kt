package p18xx

import util.expect

fun main() {
    class Solution {
        fun getMinDistance(nums: IntArray, target: Int, start: Int): Int {
            var offset = 0
            while (true) {
                arrayOf(start - offset, start + offset).forEach {
                    if (nums.getOrNull(it) == target) {
                        return offset
                    }
                }
                offset++
            }
        }
    }

    expect {
        Solution().getMinDistance(
            intArrayOf(2, 2), 1, 1
        )

    }
}
