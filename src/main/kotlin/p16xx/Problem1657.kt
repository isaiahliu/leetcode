package p16xx

import util.expect

fun main() {
    class Solution {
        fun closeStrings(word1: String, word2: String): Boolean {
            val groups1 = word1.groupingBy { it }.eachCount()
            val groups2 = word2.groupingBy { it }.eachCount()

            if (groups1.keys.sorted().joinToString("") != groups2.keys.sorted().joinToString("")) {
                return false
            }

            val counts1 = groups1.values.sorted()
            val counts2 = groups2.values.sorted()

            if (counts1.size != counts2.size) {
                return false
            }

            counts1.forEachIndexed { index, c ->
                if (c != counts2[index]) {
                    return false
                }
            }
            return true
        }
    }

    expect {
        Solution().closeStrings("", "")
    }
}

