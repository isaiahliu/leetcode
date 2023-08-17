package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestCommonSubpath(n: Int, paths: Array<IntArray>): Int {
            paths.sortBy { it.size }

            val m = 9223372036854775807L.toBigInteger()
            fun binarySearch(min: Int, max: Int): Int? {
                if (min > max) {
                    return null
                }

                val mid = (min + max) / 2

                val pathMap = hashMapOf<Long, MutableSet<Int>>()

                val base = 100001.toBigInteger()
                val baseHigh = base.modPow((mid - 1).toBigInteger(), m)

                paths.forEachIndexed { pathIndex, path ->
                    var hash = path[0].toBigInteger()

                    repeat(mid - 1) {
                        hash *= base
                        hash += path[it + 1].toBigInteger()
                        hash %= m
                    }

                    if ((pathMap[hash.toLong()]?.size ?: 0) == pathIndex) {
                        pathMap.computeIfAbsent(hash.toLong()) {
                            hashSetOf()
                        }.add(pathIndex)
                    }

                    for (i in mid until path.size) {
                        hash -= path[i - mid].toBigInteger() * baseHigh
                        hash = hash.mod(m)

                        hash *= base
                        hash += path[i].toBigInteger()
                        hash %= m

                        if ((pathMap[hash.toLong()]?.size ?: 0) == pathIndex) {
                            pathMap.computeIfAbsent(hash.toLong()) {
                                hashSetOf()
                            }.add(pathIndex)
                        }
                    }
                }

                return if (pathMap.any { it.value.size == paths.size }) {
                    binarySearch(mid + 1, max) ?: mid
                } else {
                    binarySearch(min, mid - 1)
                }
            }

            return binarySearch(1, paths[0].size) ?: 0
        }
    }

    measureTimeMillis {
        Solution().longestCommonSubpath(
            5, arrayOf(
                intArrayOf(0, 1, 2, 3, 4), intArrayOf(2, 3, 4), intArrayOf(4, 0, 1, 2, 3)
            )
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}