package p08xx

import util.expect

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

    expect {
        Solution().shiftingLetters(
            "", intArrayOf()
        )
    }
}