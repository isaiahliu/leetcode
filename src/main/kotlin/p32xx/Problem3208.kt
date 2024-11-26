package p32xx

import util.expect

fun main() {
    class Solution {
        fun numberOfAlternatingGroups(colors: IntArray, k: Int): Int {
            var diffCount = (colors.size - k + 1 until colors.lastIndex).count {
                colors[it] != colors[it + 1]
            }
            return colors.indices.count { index ->
                diffCount += colors[index] xor colors[(index - 1).mod(colors.size)]

                k - 1 == diffCount.also {
                    diffCount -= colors[(index - k + 1).mod(colors.size)] xor colors[(index - k + 2).mod(colors.size)]
                }
            }
        }
    }
    expect {
        Solution().numberOfAlternatingGroups(
            intArrayOf(), 1
        )
    }
}
