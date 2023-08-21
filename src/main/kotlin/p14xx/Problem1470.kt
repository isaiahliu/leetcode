package p14xx

import util.expect

fun main() {
    class Solution {
        fun shuffle(nums: IntArray, n: Int): IntArray {
            return IntArray(n * 2) {
                nums[n * (it % 2) + it / 2]
            }
        }
    }

    expect {
        Solution().shuffle(
            intArrayOf(2, 5, 1, 3, 4, 7), 3
        ).toList()

    }
}

