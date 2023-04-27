package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestStrChain(words: Array<String>): Int {
            val groups = words.indices.groupBy { words[it].length }.entries.sortedBy { it.key }

            fun String.like(target: String): Boolean {
                var l = 0
                var r = 0

                while (l < length) {
                    if (this[l] != (target.getOrNull(r) ?: break)) {
                        if (l == r) {
                            l++
                        } else {
                            return false
                        }
                    } else {
                        l++
                        r++
                    }
                }

                return true
            }

            var result = 1
            val lengths = Array(words.size) { 1 }

            for (groupIndex in 1 until groups.size) {
                val cur = groups[groupIndex]
                val pre = groups[groupIndex - 1]
                if (cur.key == pre.key + 1) {
                    cur.value.forEach { c ->
                        pre.value.forEach { p ->
                            if (words[c].like(words[p])) {
                                lengths[c] = lengths[c].coerceAtLeast(lengths[p] + 1)

                                result = result.coerceAtLeast(lengths[c])
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().longestStrChain(
            arrayOf("a", "ab")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}