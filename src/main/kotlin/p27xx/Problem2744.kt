package p27xx

import util.expect

fun main() {
    class Solution {
        fun maximumNumberOfStringPairs(words: Array<String>): Int {
            var result = 0
            val counts = hashMapOf<String, Int>()

            words.forEach {
                result += counts[it.reversed()] ?: 0

                counts[it] = (counts[it] ?: 0) + 1
            }

            return result
        }
    }

    expect {
        Solution().maximumNumberOfStringPairs(
            arrayOf()
        )
    }
}
