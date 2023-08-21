package p18xx

import util.expect

fun main() {
    class Solution {
        fun makeEqual(words: Array<String>): Boolean {
            val counts = IntArray(26)

            words.forEach {
                it.forEach {
                    counts[it - 'a']++
                }
            }

            return counts.all { it % words.size == 0 }
        }
    }

    expect {
        Solution().makeEqual(
            arrayOf()
        )
    }
}
