package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun squareIsWhite(coordinates: String): Boolean {
            return coordinates.toCharArray().let { (row, column) -> (row - 'a' xor column - '0') and 1 == 0 }
        }
    }

    measureTimeMillis {
        Solution().squareIsWhite(
            "a1"
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
