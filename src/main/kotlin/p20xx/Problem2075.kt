package p20xx

import util.expect

fun main() {
    class Solution {
        fun decodeCiphertext(encodedText: String, rows: Int): String {
            return String(CharArray((encodedText.length - (rows - 1) * (rows - 1)).coerceAtLeast(0)) {
                encodedText[it % rows * (encodedText.length / rows + 1) + it / rows]
            }).trimEnd()
        }
    }

    expect {
        Solution().decodeCiphertext(
            "", 5
        )
    }
}