package p08xx

import util.expect

fun main() {
    class Solution {
        fun stoneGame(piles: IntArray): Boolean {
            val total = piles.sum()

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun pick(startIndex: Int, endIndex: Int, remaining: Int): Int {
                val cacheKey = startIndex to endIndex
                return when {
                    startIndex == endIndex -> remaining
                    cacheKey in cache -> cache[cacheKey] ?: 0
                    else -> {
                        val pickLeft = pick(startIndex + 1, endIndex, remaining - piles[startIndex])
                        val pickRight = pick(startIndex, endIndex - 1, remaining - piles[endIndex])

                        val result = remaining - pickLeft.coerceAtMost(pickRight)

                        cache[cacheKey] = result
                        result
                    }
                }
            }

            val alice = pick(0, piles.lastIndex, total)

            return alice * 2 > total
        }
    }

    expect {
        Solution().stoneGame(
            intArrayOf(2, 3)
        )

    }
}