package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun topKFrequent(words: Array<String>, k: Int): List<String> {
            return words.toList().groupingBy { it }
                .eachCount().entries.sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
                .take(k).map { it.key }
        }
    }

    measureTimeMillis {
        Solution().topKFrequent(
            arrayOf(), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}