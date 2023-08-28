package p21xx

import util.expect

fun main() {
    class Solution {
        fun firstPalindrome(words: Array<String>): String {
            return words.firstOrNull {
                var index = 0

                while (index * 2 < it.lastIndex) {
                    if (it[index] == it[it.lastIndex - index]) {
                        return@firstOrNull false
                    } else {
                        index++
                    }
                }

                true
            }.orEmpty()
        }
    }

    expect {
        Solution().firstPalindrome(
            arrayOf(
                "", ""
            )
        )
    }
}