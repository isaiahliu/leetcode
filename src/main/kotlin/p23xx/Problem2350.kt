package p23xx

import util.expect

fun main() {
    class Solution {
        fun shortestSequence(rolls: IntArray, k: Int): Int {
            val fullSize = hashSetOf<Int>()

            var next = 1

            rolls.forEach {
                fullSize.add(it)

                if (fullSize.size == k) {
                    fullSize.clear()
                    next++
                }
            }

            return next
        }
    }

    expect {
        Solution().shortestSequence(
            intArrayOf(4, 2, 1, 2, 3, 3, 2, 4, 1), 4
        )
    }
}