package p20xx

import util.expect

fun main() {
    class Solution {
        fun decodeCiphertext(encodedText: String, rows: Int): String {
            if (encodedText.isEmpty()) {
                return ""
            }

            val size = encodedText.length / rows

            return String(CharArray(encodedText.length - (rows - 1) * (rows - 1)) {
                encodedText[it % rows * (size + 1) + it / rows]
            }).trimEnd()
        }
    }

    expect(1) {
        Solution().decodeCiphertext(
            "", 5
        )
    }
}