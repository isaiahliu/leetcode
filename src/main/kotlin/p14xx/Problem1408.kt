package p14xx

import util.expect

fun main() {
    class Solution {
        fun stringMatching(words: Array<String>): List<String> {
            words.sortWith(compareByDescending { it.length })

            val visited = hashSetOf<String>()

            val result = arrayListOf<String>()
            words.forEach {
                if (visited.any { p -> it in p }) {
                    result.add(it)
                }

                visited.add(it)
            }

            return result
        }
    }

    expect {
        Solution().stringMatching(
            arrayOf()
        )
    }
}

