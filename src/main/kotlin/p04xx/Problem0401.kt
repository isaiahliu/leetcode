package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun readBinaryWatch(turnedOn: Int): List<String> {
            val result = arrayListOf<String>()
            repeat(12) { h ->
                repeat(60) { m ->
                    if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                        result += "$h:${m.toString().padStart(2, '0')}"
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().readBinaryWatch(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


