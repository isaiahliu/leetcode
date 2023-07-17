package p01xx

import util.input
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
            fun String.similar(target: String): Boolean {
                return this.filterIndexed { index, c -> c != target[index] }.length == 1
            }

            val allWords = wordList.toMutableList()

            val stepMap = hashMapOf(0 to listOf(beginWord))
            val steps = arrayListOf(beginWord)
            var step = 0
            while (true) {
                steps.toList().also { steps.clear() }.forEach { task ->
                    allWords.filter { it.similar(task) }.forEach {
                        steps.add(it)
                        allWords.remove(it)
                    }
                }

                if (steps.isEmpty()) {
                    return 0
                }

                stepMap[++step] = steps.toList()

                if (endWord in steps) {
                    break
                }
            }

            return step + 1
        }
    }

    measureTimeMillis {
        Solution().ladderLength(
            "sand", "acne", input
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

