package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun frequencySort(s: String): String {
            return s.groupingBy { it }.eachCount().entries.sortedByDescending { it.value }
                .joinToString("") { (key, value) ->
                    key.toString().repeat(value)
                }
        }
    }

    measureTimeMillis {
        Solution().frequencySort("").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}