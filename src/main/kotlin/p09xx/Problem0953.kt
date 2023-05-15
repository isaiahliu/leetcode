package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isAlienSorted(words: Array<String>, order: String): Boolean {
            val seq = IntArray(26)
            order.forEachIndexed { index, c ->
                seq[c - 'a'] = index
            }

            val comparator = Comparator { a: String, b: String ->
                repeat(a.length.coerceAtLeast(b.length)) {
                    val l = a.getOrNull(it)?.let { seq[it - 'a'] } ?: -1
                    val r = b.getOrNull(it)?.let { seq[it - 'a'] } ?: -1

                    when {
                        l < r -> return@Comparator -1
                        l > r -> return@Comparator 1
                    }
                }
                0
            }
            for (i in 1 until words.size) {
                if (comparator.compare(words[i], words[i - 1]) < 0) {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isAlienSorted(
            arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
