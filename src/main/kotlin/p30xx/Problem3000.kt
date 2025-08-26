package p30xx

import util.expect

fun main() {
    class Solution {
        fun areaOfMaxDiagonal(dimensions: Array<IntArray>): Int {
            return dimensions.maxWith(compareBy<IntArray> { (a, b) -> a * a + b * b }.thenBy { (a, b) -> a * b })
                .let { (a, b) -> a * b }
        }
    }

    expect {
        Solution().areaOfMaxDiagonal(
            arrayOf()
        )
    }
}
