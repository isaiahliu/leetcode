package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mergeStones(stones: IntArray, k: Int): Int {
            operator fun Pair<Int, Int>.plus(target: Pair<Int, Int>): Pair<Int, Int> {
                return first + target.first to second + target.second
            }

            fun Pair<Int, Int>.lessThan(target: Pair<Int, Int>?): Boolean {
                target ?: return true

                return first + second < target.first + target.second
            }

            fun Pair<Int, Int>.add(part: Int): Pair<Int, Int> {
                return if (part == k) {
                    first to first + second
                } else {
                    this
                }
            }

            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Pair<Int, Int>?>()
            fun calculate(startIndex: Int, endIndex: Int, part: Int): Pair<Int, Int>? {
                return when {
                    startIndex == endIndex -> {
                        stones[startIndex] to -stones[startIndex]
                    }

                    part == k && (endIndex - startIndex) % (k - 1) != 0 -> {
                        null
                    }

                    else -> {
                        var min: Pair<Int, Int>? = null

                        val cacheKey = startIndex to endIndex to part
                        if (cacheKey in cache) {
                            return cache[cacheKey]
                        }

                        for (i in startIndex until endIndex) {
                            calculate(startIndex, i, k)?.add(part)?.takeIf { it.lessThan(min) }?.let { l ->
                                calculate(i + 1, endIndex, (part - 1).takeIf { it > 1 } ?: k)?.add(part)?.let { r ->
                                    l + r
                                }
                            }?.takeIf { it.lessThan(min) }?.let {
                                min = it
                            }
                        }

                        cache[cacheKey] = min
                        min
                    }
                }
            }

            return calculate(0, stones.lastIndex, k)?.let { it.first + it.second } ?: -1
        }
    }

    measureTimeMillis {
        Solution().mergeStones(
            intArrayOf(1), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}