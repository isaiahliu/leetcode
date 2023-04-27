package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun hasGroupsSizeX(deck: IntArray): Boolean {
            return deck.toList().groupingBy { it }.eachCount().values.map { it.toBigInteger() }
                .reduce { a, b -> a.gcd(b) }
                .toInt() > 1
        }
    }

    measureTimeMillis {
        Solution().hasGroupsSizeX(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}