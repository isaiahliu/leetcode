package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mergeStones(stones: IntArray, k: Int): Int {
            operator fun Pair<Int, Int>.plus(target: Pair<Int, Int>): Pair<Int, Int> {
                return first + target.first to second + target.second
            }

            fun min(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Pair<Int, Int> {
                return if (p1.first + p1.second <= p2.first + p2.second) {
                    p1
                } else {
                    p2
                }
            }

            val MAX = 99999999 to 99999999
            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Pair<Int, Int>>()
            fun calculate(startIndex: Int, endIndex: Int, part: Int): Pair<Int, Int> {
                return when {
                    startIndex == endIndex -> {
                        stones[startIndex] to -stones[startIndex]
                    }

                    part == k && (endIndex - startIndex) % (k - 1) != 0 -> {
                        MAX
                    }

                    else -> {
                        var min = MAX

                        val cacheKey = startIndex to endIndex to part
                        if (cacheKey in cache) {
                            return cache[cacheKey] ?: MAX
                        }

                        for (i in startIndex until endIndex) {
                            val otherPart = (part - 1).takeIf { it > 1 } ?: k
                            arrayOf(
                                calculate(startIndex, i, k) + calculate(i + 1, endIndex, otherPart),
                                calculate(startIndex, i, otherPart) + calculate(i + 1, endIndex, k)
                            ).map {
                                if (k == part) {
                                    it.first to it.first + it.second
                                } else {
                                    it
                                }
                            }.forEach {
                                min = min(min, it)
                            }
                        }

                        cache[cacheKey] = min
                        min
                    }
                }
            }

            return calculate(0, stones.lastIndex, k).let { it.first + it.second }.takeIf { it < MAX.first } ?: -1
        }
    }

    measureTimeMillis {
        Solution().mergeStones(
            intArrayOf(1), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}