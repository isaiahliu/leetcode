package p06xx

import util.expect

fun main() {
    class Solution {
        fun topKFrequent(words: Array<String>, k: Int): List<String> {
            return words.toList().groupingBy { it }
                .eachCount().entries.sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
                .take(k).map { it.key }
        }
    }

    expect {
        Solution().topKFrequent(
            arrayOf(), 1
        )
    }
}