package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTheDifference(s: String, t: String): Char {
            var sum = 0
            t.forEach {
                sum += it - 'a'
            }
            s.forEach {
                sum -= it - 'a'
            }

            return 'a' + sum
        }
    }

    measureTimeMillis {
        Solution().findTheDifference(
            "abcd", "abcde"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

