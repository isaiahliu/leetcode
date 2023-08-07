package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countConsistentStrings(allowed: String, words: Array<String>): Int {
            return allowed.toSet().let { allowedSet -> words.count { it.all { it in allowedSet } } }
        }
    }

    measureTimeMillis {
        Solution().countConsistentStrings(
            "", arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

