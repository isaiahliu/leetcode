package p25xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun divisibilityArray(word: String, m: Int): IntArray {
            var rem = 0L
            return IntArray(word.length) {
                rem = (rem * 10 + (word[it] - '0')) % m

                rem.sign xor 1
            }
        }
    }

    expect {
        Solution().divisibilityArray(
            "", 1
        )
    }
}