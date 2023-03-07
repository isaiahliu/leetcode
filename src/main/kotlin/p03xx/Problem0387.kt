package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun firstUniqChar(s: String): Int {
            val array = IntArray(26) { s.length }

            s.forEachIndexed { index, c ->
                when (array[c - 'a']) {
                    s.length -> {
                        array[c - 'a'] = index
                    }

                    Int.MAX_VALUE -> {
                    }

                    else -> {
                        array[c - 'a'] = Int.MAX_VALUE
                    }
                }
            }

            return array.min().takeIf { it < s.length } ?: -1
        }
    }

    measureTimeMillis {
        Solution().firstUniqChar(
            "leetcode"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

