package p31xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray): Int {
            var result = 0

            var t = 0
            nums.forEach {
                if (it xor t == 0) {
                    result++
                    t = t xor 1
                }
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf()
        )
    }
}
