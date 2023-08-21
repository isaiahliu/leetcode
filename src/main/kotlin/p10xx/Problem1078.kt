package p10xx

import util.expect

fun main() {
    class Solution {
        fun findOcurrences(text: String, first: String, second: String): Array<String> {
            val words = text.split(" ")

            val result = arrayListOf<String>()
            for (i in 2 until words.size) {
                if (words[i - 2] == first && words[i - 1] == second) {
                    result.add(words[i])
                }
            }

            return result.toTypedArray()
        }
    }

    expect {
        Solution().findOcurrences(
            "DB", "", ""
        )
    }
}