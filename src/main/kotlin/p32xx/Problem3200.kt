package p32xx

import util.expect

fun main() {
    class Solution {
        fun maxHeightOfTriangle(red: Int, blue: Int): Int {
            fun find(top: Int, color1: Int, color2: Int): Int {
                return if (top > color1) {
                    0
                } else {
                    find(top + 1, color2, color1 - top) + 1
                }
            }

            return maxOf(find(1, red, blue), find(1, blue, red))
        }
    }

    expect {
        Solution().maxHeightOfTriangle(
            3, 21
        )
    }
}
