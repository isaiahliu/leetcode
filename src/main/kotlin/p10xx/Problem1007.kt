package p10xx

import util.expect

fun main() {
    class Solution {
        fun minDominoRotations(tops: IntArray, bottoms: IntArray): Int {
            var result = Int.MAX_VALUE
            setOf(tops[0], bottoms[0]).forEach { target ->
                var topTimes = 0
                var bottomTimes = 0
                for (index in tops.indices) {
                    val topMatch = tops[index] == target
                    val bottomMatch = bottoms[index] == target
                    when {
                        topMatch && bottomMatch -> {}
                        topMatch -> {
                            bottomTimes++
                        }

                        bottomMatch -> {
                            topTimes++
                        }

                        else -> {
                            return@forEach
                        }
                    }
                }

                result = result.coerceAtMost(topTimes).coerceAtMost(bottomTimes)
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    expect {
        Solution().minDominoRotations(
            intArrayOf(), intArrayOf()
        )
    }
}
