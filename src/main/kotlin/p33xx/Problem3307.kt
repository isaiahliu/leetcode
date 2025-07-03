package p33xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun kthCharacter(k: Long, operations: IntArray): Char {
            var index = -1
            var offset = 0
            while ((1L shl ++index) < k) {
                offset += ((k - 1) and (1L shl index)).sign * operations[index]
            }

            return 'a' + offset % 26
        }
    }

    expect {
        Solution().kthCharacter(
            10, intArrayOf()
        )
    }
}
