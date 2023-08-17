package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun eliminateMaximum(dist: IntArray, speed: IntArray): Int {
            val sorted = dist.indices.map {
                dist[it] / speed[it] + if (dist[it] % speed[it] > 0) 1 else 0
            }.sorted()

            var result = 0

            for ((index, minute) in sorted.withIndex()) {
                if (minute > index) {
                    result++
                } else {
                    break
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().eliminateMaximum(
            intArrayOf(-1, 0, 0, 1, 2), intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}