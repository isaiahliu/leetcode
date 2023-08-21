package p06xx

import util.expect

fun main() {
    class Solution {
        fun maximumSwap(num: Int): Int {
            val chars = num.toString().toCharArray()

            val sorted = chars.mapIndexed { index, c -> index to c }
                .sortedWith(compareByDescending<Pair<Int, Char>> { it.second }.thenBy { it.first })

            for ((index, pair) in sorted.withIndex()) {
                if (pair.first != index) {
                    for (j in chars.lastIndex downTo index) {
                        if (chars[j] == pair.second) {
                            chars[j] = chars[index]
                            chars[index] = pair.second
                        }
                    }
                    break
                }
            }

            return String(chars).toInt()
        }
    }

    expect {
        Solution().maximumSwap(
            1993
        )
        Solution().maximumSwap(
            98368
        )
    }
}