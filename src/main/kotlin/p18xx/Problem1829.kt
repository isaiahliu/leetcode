package p18xx

import util.expect

fun main() {
    class Solution {
        fun getMaximumXor(nums: IntArray, maximumBit: Int): IntArray {
            val mask = (1 shl maximumBit) - 1
            var sum = nums.fold(0) { a, b -> a xor b }

            return IntArray(nums.size) {
                val left = sum / (1 shl maximumBit)
                val right = sum % (1 shl maximumBit)

                sum = sum xor nums[nums.lastIndex - it]

                left + right.inv() and mask
            }
        }
    }

    expect {
        Solution().getMaximumXor(
            intArrayOf(), 1
        )

    }
}
