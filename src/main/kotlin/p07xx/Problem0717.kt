package p07xx

import util.expect

fun main() {
    class Solution {
        fun isOneBitCharacter(bits: IntArray): Boolean {
            var followCount = 0
            bits.dropLast(1).forEach {
                if (followCount-- == 0) {
                    followCount = it
                }
            }

            return followCount == 0
        }
    }

    expect {
        Solution().isOneBitCharacter(
            intArrayOf(1, 0, 0)
        )
    }
}