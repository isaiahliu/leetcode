package p30xx

import util.expect

fun main() {
    class Solution {
        fun isPossibleToSplit(nums: IntArray): Boolean {
            val set1 = hashSetOf<Int>()
            val set2 = hashSetOf<Int>()

            return nums.all { set1.add(it) || set2.add(it) }
        }
    }

    expect {
        Solution().isPossibleToSplit(
            intArrayOf(1, 1, 0, 0, 0, 1, 1, 0, 0, 1), 3
        )
    }
}
