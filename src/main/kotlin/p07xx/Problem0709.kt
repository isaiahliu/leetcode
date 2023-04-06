package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun toLowerCase(s: String): String {
            return String(s.map {
                if (it in 'A'..'Z') {
                    'a' + (it - 'A')
                } else {
                    it
                }
            }.toCharArray())
        }
    }

    measureTimeMillis {
        Solution().toLowerCase("here").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}