package p17xx

import util.expect

fun main() {
    class Solution {
        fun halvesAreAlike(s: String): Boolean {
            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

            val counts = intArrayOf(0, 0)

            s.forEachIndexed { index, c ->
                if (c in vowels) {
                    counts[index / (s.length / 2)]++
                }
            }

            return counts[0] == counts[1]
        }
    }

    expect {
        Solution().halvesAreAlike(
            "aaab"
        )
    }
}
