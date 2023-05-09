package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reorderLogFiles(logs: Array<String>): Array<String> {
            val comparator = Comparator { a: Pair<Int, List<String>>, b: Pair<Int, List<String>> ->
                val (i1, tokens1) = a
                val (i2, tokens2) = b

                val digit1 = tokens1[1][0] in '0'..'9'
                val digit2 = tokens2[1][0] in '0'..'9'

                when {
                    digit1 && digit2 -> i1 - i2
                    digit1 && !digit2 -> 1
                    digit2 && !digit1 -> -1
                    else -> {
                        var comp = tokens1[1].compareTo(tokens2[1])
                        if (comp == 0) {
                            comp = tokens1[0].compareTo(tokens2[0])
                        }

                        comp
                    }
                }
            }
            return logs.mapIndexed { index, s -> index to s.split(' ', limit = 2) }.sortedWith(comparator)
                .map { logs[it.first] }.toTypedArray()
        }
    }

    measureTimeMillis {
        Solution().reorderLogFiles(
            arrayOf("dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero")
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}