package p24xx

import util.expect

fun main() {
    class Solution {
        fun minimizeXor(num1: Int, num2: Int): Int {
            var result = 0
            var remain = num1
            var count = num2.countOneBits()

            while (count > 0 && remain > 0) {
                count--
                remain.takeHighestOneBit().also {
                    result += it
                    remain -= it
                }
            }

            var pos = 0
            while (count > 0) {
                if (result and (1 shl pos) == 0) {
                    result += 1 shl pos
                    count--
                }

                pos++
            }

            return result
        }
    }

    expect {
        Solution().minimizeXor(
            5, 6
        )
    }
}