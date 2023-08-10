package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkPartitioning(s: String): Boolean {
            val leftMatches = hashSetOf(0)
            val rightMatches = hashSetOf(s.lastIndex)
            val midMatches = hashSetOf<Pair<Int, Int>>()
            if (s[0] == s[1]) {
                leftMatches.add(1)
            }

            for (midIndex in 1 until s.lastIndex) {
                midMatches.add(midIndex to midIndex)

                arrayOf(midIndex to midIndex + 1, midIndex - 1 to midIndex + 1).forEach {
                    var left = it.first
                    var right = it.second

                    while (left in s.indices && right in s.indices && s[left] == s[right]) {
                        when {
                            left == 0 -> {
                                leftMatches.add(right)
                            }

                            right == s.lastIndex -> {
                                rightMatches.add(left)
                            }

                            else -> {
                                midMatches.add(left to right)
                            }
                        }

                        left--
                        right++
                    }
                }
            }

            return midMatches.any { (left, right) -> left - 1 in leftMatches && right + 1 in rightMatches }
        }
    }

    measureTimeMillis {
        Solution().checkPartitioning(
            ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
