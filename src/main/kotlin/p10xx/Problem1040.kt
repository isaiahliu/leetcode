package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numMovesStonesII(stones: IntArray): IntArray {
            stones.sort()

            if (stones[stones.lastIndex] - stones[0] + 1 == stones.size) {
                return intArrayOf(0, 0)
            }

            val emptyCount = stones[stones.lastIndex] - stones[0] + 1 - stones.size
            val leftEmpty = stones[1] - stones[0] - 1
            val rightEmpty = stones[stones.lastIndex] - stones[stones.lastIndex - 1] - 1

            val max = emptyCount - leftEmpty.coerceAtMost(rightEmpty)
            var min = Int.MAX_VALUE

            var leftIndex = 0
            var rightIndex = 0

            while (rightIndex < stones.lastIndex) {
                while (rightIndex < stones.lastIndex && stones[rightIndex + 1] - stones[leftIndex] + 1 <= stones.size) {
                    rightIndex++
                }

                val readyStones = rightIndex - leftIndex + 1
                val empty = stones.size - readyStones
                val bothEdgeReady = stones[rightIndex] - stones[leftIndex] + 1 == stones.size

                min = min.coerceAtMost(
                    if (bothEdgeReady || empty > 1) {
                        empty
                    } else {
                        2
                    }
                )

                leftIndex++
            }

            return intArrayOf(min, max)
        }
    }

    measureTimeMillis {
        Solution().numMovesStonesII(
            intArrayOf(4, 7, 9)
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}