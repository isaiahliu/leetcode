package p32xx

import util.expect

fun main() {
    class Solution {
        fun numberOfAlternatingGroups(colors: IntArray): Int {
            return colors.indices.count { index ->
                colors[index] != colors[(index + 1).mod(colors.size)] && colors[index] != colors[(index - 1).mod(colors.size)]
            }
        }
    }

    expect {
        Solution().numberOfAlternatingGroups(
            intArrayOf()
        )
    }
}
