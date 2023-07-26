package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSatisfaction(satisfaction: IntArray): Int {
            val results = satisfaction.filter { it >= 0 }.sorted().toMutableList()
            val negatives = satisfaction.filter { it < 0 }.sortedDescending()

            var result = results.mapIndexed { index, i -> i * (index + 1) }.sum()
            val posAdd = results.sum()
            var negAdd = 0
            for (neg in negatives) {
                if (posAdd + negAdd + neg >= 0) {
                    negAdd += neg
                    result += posAdd + negAdd
                } else {
                    break
                }
            }

            return result
        }
    }
    measureTimeMillis {
        Solution().maxSatisfaction(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

