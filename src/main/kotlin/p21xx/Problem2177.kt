package p21xx

import util.expect

fun main() {
    class Solution {
        fun sumOfThree(num: Long): LongArray {
            return num.takeIf { it % 3 == 0L }?.let { num / 3 }?.let {
                longArrayOf(it - 1, it, it + 1)
            } ?: longArrayOf()
        }
    }

    expect {
        Solution().sumOfThree(
            10
        )
    }
}