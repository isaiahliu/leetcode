package p25xx

import util.expect

fun main() {
    class Solution {
        fun similarPairs(words: Array<String>): Int {
            val counts = hashMapOf<Int, Int>()

            words.forEach {
                var word = 0

                it.forEach {
                    word = word or (1 shl it - 'a')
                }

                counts[word] = (counts[word] ?: 0) + 1
            }

            return counts.values.sumOf {
                it * (it - 1) / 2
            }
        }
    }

    expect {
        Solution().similarPairs(
            arrayOf()
        )
    }
}