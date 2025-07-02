package p33xx

import util.expect

fun main() {
    class Solution {
        fun kthCharacter(k: Long, operations: IntArray): Char {
            var operationIndex = 0
            var t = k - 1
            var offset = 0
            while (t > 0) {
                if (t % 2 == 1L && operations[operationIndex] > 0) {
                    offset++
                }

                operationIndex++
                t /= 2
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
