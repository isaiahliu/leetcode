package p22xx

import util.expect

fun main() {
    class Solution {
        fun minDeletion(nums: IntArray): Int {
            var size = 0

            var left = true
            var pre = 0
            nums.forEach {
                if (left) {
                    pre = it
                    left = false
                    size++
                } else if (it != pre) {
                    left = true
                    size++
                }
            }

            return nums.size - size / 2 * 2
        }
    }

    expect {
        Solution().minDeletion(
            intArrayOf()
        )
    }
}