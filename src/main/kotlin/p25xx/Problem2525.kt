package p25xx

import util.expect

fun main() {
    class Solution {
        fun categorizeBox(length: Int, width: Int, height: Int, mass: Int): String {
            var result = 0

            if (mass >= 100) {
                result += 0b01
            }

            if (maxOf(length, width, height) >= 1e4 || 1L * length * height * width >= 1e9) {
                result += 0b10
            }

            return when (result) {
                0b00 -> "Neither"
                0b01 -> "Heavy"
                0b10 -> "Bulky"
                else -> "Both"
            }
        }
    }

    expect {
        Solution().categorizeBox(
            2909, 3968, 3272, 400
        )
    }
}