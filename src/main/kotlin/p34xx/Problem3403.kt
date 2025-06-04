package p34xx

import util.expect

fun main() {
    class Solution {
        fun answerString(word: String, numFriends: Int): String {
            if (numFriends == 1) {
                return word
            }

            val sb = StringBuilder()

            var result = ""
            word.forEach {
                sb.append(it)
                if (sb.length > word.length - numFriends + 1) {
                    sb.deleteAt(0)
                }

                if (sb.length == word.length - numFriends + 1) {
                    result = maxOf(result, sb.toString())
                }
            }

            while (sb.length > 1) {
                sb.deleteAt(0)
                result = maxOf(result, sb.toString())
            }

            return result
        }
    }

    expect {
        Solution().answerString(
            "bif", 2
        )
    }
}
