package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun uncommonFromSentences(s1: String, s2: String): Array<String> {
            val dup = hashSetOf<String>()
            val visited = hashSetOf<String>()

            "$s1 $s2".split(" ").forEach {
                if (it !in dup) {
                    if (!visited.add(it)) {
                        visited.remove(it)
                        dup.add(it)
                    }
                }
            }

            return visited.toTypedArray()
        }
    }

    measureTimeMillis {
        Solution().uncommonFromSentences(
            "this apple is sweet", "this apple is sour"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}