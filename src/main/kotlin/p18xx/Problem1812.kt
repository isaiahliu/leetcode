package p18xx

import util.expect

fun main() {
    class Solution {
        fun squareIsWhite(coordinates: String): Boolean {
            return coordinates.toCharArray().let { (row, column) -> (row - 'a' xor column - '0') and 1 == 0 }
        }
    }

    expect {
        Solution().squareIsWhite(
            "a1"
        )
    }
}
