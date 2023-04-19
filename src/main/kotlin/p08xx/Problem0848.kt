package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shiftingLetters(s: String, shifts: IntArray): String {
            shifts[shifts.lastIndex] %= 26

            for (i in shifts.lastIndex - 1 downTo 0) {
                shifts[i] = shifts[i] % 26 + shifts[i + 1]
            }

            return String(s.mapIndexed { index, c ->
                'a' + (c - 'a' + shifts[index]) % 26
            }.toCharArray())
        }
    }

    measureTimeMillis {
        Solution().shiftingLetters(
            "", intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}