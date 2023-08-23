package p20xx

import util.expect

fun main() {
    class Solution {
        fun stoneGameIX(stones: IntArray): Boolean {
            val counts = LongArray(3)

            stones.forEach {
                counts[it % 3]++
            }

            val (zero, one, two) = counts

            return if (zero % 2 == 0L) {
                one * two >= 1L
            } else {
                one - two !in -2L..2L
            }
        }
    }

    expect {
        Solution().stoneGameIX(
            intArrayOf()
        )
    }
}