package p23xx

import util.expect

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

    expect {
        Solution().mergeSimilarItems(emptyArray(), emptyArray())
    }
}

