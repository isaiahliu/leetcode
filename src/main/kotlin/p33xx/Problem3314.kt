package p33xx

import util.expect

fun main() {
    class Solution {
        fun minBitwiseArray(nums: List<Int>): IntArray {
            return nums.map {
                if (it % 2 == 0) {
                    -1
                } else {
                    var p = 1
                    while (it and p > 0) {
                        p = p shl 1
                    }
                    it - (p shr 1)
                }
            }.toIntArray()
        }
    }

    expect {
        Solution().minBitwiseArray(
            listOf()
        )
    }
}
