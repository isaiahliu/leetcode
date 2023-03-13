package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun repeatedSubstringPattern(s: String): Boolean {
            val set = hashSetOf<Int>()

            s.forEachIndexed { index, c ->
                set.toSet().forEach { i ->
                    if (s[index % i] != c) {
                        set.remove(i)
                    }
                }

                if (s.length % (index + 1) == 0) {
                    set.add(index + 1)
                }
            }

            return set.size > 1
        }
    }

    measureTimeMillis {
        Solution().repeatedSubstringPattern("abcabcabd").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}