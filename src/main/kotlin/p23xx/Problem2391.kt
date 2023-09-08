package p23xx

import util.expect

fun main() {
    class Solution {
        fun garbageCollection(garbage: Array<String>, travel: IntArray): Int {
            for (i in 1 until travel.size) {
                travel[i] += travel[i - 1]
            }

            val counts = intArrayOf(0, 0, 0)
            val moves = intArrayOf(0, 0, 0)
            garbage.forEachIndexed { index, s ->
                s.forEach {
                    val garbageIndex = when (it) {
                        'M' -> 0
                        'G' -> 1
                        else -> 2
                    }
                    counts[garbageIndex]++
                    moves[garbageIndex] = travel.getOrNull(index - 1) ?: 0
                }
            }

            return counts.sum() + moves.sum()
        }
    }

    expect {
        Solution().garbageCollection(
            arrayOf(), intArrayOf()
        )
    }
}