package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arrangeWords(text: String): String {
            return text.split(" ").mapIndexed { index, s -> s to index }
                .sortedWith(compareBy<Pair<String, Int>> { it.first.length }.thenBy { it.second })
                .mapIndexed { index, (s, _) ->
                    if (index == 0) {
                        s.take(1).uppercase() + s.drop(1)
                    } else {
                        s.lowercase()
                    }
                }.joinToString(" ")
        }
    }

    measureTimeMillis {
        Solution().arrangeWords(
            ""
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

