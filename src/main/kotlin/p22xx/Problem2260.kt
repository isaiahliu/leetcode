package p22xx

import util.expect

fun main() {
    class Solution {
        fun minimumCardPickup(cards: IntArray): Int {
            var result = Int.MAX_VALUE

            val indices = hashMapOf<Int, Int>()
            cards.forEachIndexed { index, card ->
                indices[card]?.also {
                    result = result.coerceAtMost(index - it + 1)
                }

                indices[card] = index
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    expect {
        Solution().minimumCardPickup(
            intArrayOf()
        )
    }
}