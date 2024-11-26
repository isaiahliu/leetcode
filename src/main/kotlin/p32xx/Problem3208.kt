package p32xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun numberOfAlternatingGroups(colors: IntArray, k: Int): Int {
            return (0 until colors.size + k - 1).fold(intArrayOf(0, 0)) { acc, index ->
                acc[0] = acc[0] * (colors[index % colors.size] xor colors[(index + 1) % colors.size]) + 1
                acc[1] += (acc[0] / k).sign
                acc
            }[1]
        }
    }
    expect {
        Solution().numberOfAlternatingGroups(
            intArrayOf(0, 1, 0, 1, 0), 3
        )
    }
}
