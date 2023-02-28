package p23xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun mergeSimilarItems(items1: Array<IntArray>, items2: Array<IntArray>): List<List<Int>> {
            val map = sortedMapOf<Int, Int>()
            arrayOf(items1, items2).forEach {
                it.forEach { (key, value) ->
                    map[key] = (map[key] ?: 0) + value
                }
            }

            return map.map { (key, value) -> listOf(key, value) }
        }
    }

    measureTimeMillis {
        Solution().mergeSimilarItems(emptyArray(), emptyArray()).also { println(it) }
    }
}

