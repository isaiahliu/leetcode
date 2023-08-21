package p09xx

import util.expect

fun main() {
    class Solution {
        fun hasGroupsSizeX(deck: IntArray): Boolean {
            return deck.toList().groupingBy { it }.eachCount().values.map { it.toBigInteger() }
                .reduce { a, b -> a.gcd(b) }
                .toInt() > 1
        }
    }

    expect {
        Solution().hasGroupsSizeX(
            intArrayOf()
        )
    }
}