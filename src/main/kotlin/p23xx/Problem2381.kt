package p23xx

import util.expect

fun main() {
    class Solution {
        fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
            val diff = IntArray(s.length + 1)

            shifts.forEach { (from, to, direction) ->
                if (direction == 1) {
                    diff[from]++
                    diff[to + 1]--
                } else {
                    diff[from]--
                    diff[to + 1]++
                }
            }

            return s.mapIndexed { index, ch ->
                diff[index] += diff.getOrNull(index - 1) ?: 0

                'a' + (ch - 'a' + diff[index]).mod(26)
            }.joinToString("")
        }
    }

    expect {
        Solution().shiftingLetters(
            "abc", arrayOf(
                intArrayOf(0, 1, 0)
            )
        )
    }
}