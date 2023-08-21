package p10xx

import util.expect

fun main() {
    class Solution {
        fun numMovesStones(a: Int, b: Int, c: Int): IntArray {
            val (x, y, z) = arrayOf(a, b, c).sorted()

            var min = 0
            val max = z - x - 2

            if (z - x > 2) {
                min = 1
                if (y - x > 2 && z - y > 2) {
                    min++
                }
            }

            return intArrayOf(min, max)
        }
    }

    expect {
        Solution().numMovesStones(
            1, 2, 5
        )
    }
}
