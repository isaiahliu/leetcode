package p18xx

import util.expect

fun main() {
    class Solution {
        fun checkIfPangram(sentence: String): Boolean {
            return sentence.toSet().size == 26
        }
    }

    expect {
        Solution().checkIfPangram(
            "thequickbrownfoxjumpsoverthelazydog"
        )

    }
}
