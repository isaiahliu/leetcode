package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun readBinaryWatch(turnedOn: Int): List<String> {
            return (0 until 12).map { h ->
                (0 until 12).filter { m ->
                    Integer.bitCount(h) + Integer.bitCount(m) == turnedOn
                }.map { m -> "$h:${m.toString().padStart(2, '0')}" }
            }.flatten()
        }
    }

    measureTimeMillis {
        Solution().readBinaryWatch(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


