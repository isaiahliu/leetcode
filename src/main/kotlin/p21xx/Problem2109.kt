package p21xx

import util.expect

fun main() {
    class Solution {
        fun addSpaces(s: String, spaces: IntArray): String {
            val result = StringBuilder()

            var index = 0
            var spaceIndex = 0

            while (index < s.length) {
                if (spaceIndex < spaces.size && index == spaces[spaceIndex]) {
                    spaceIndex++
                    result.append(" ")
                }

                result.append(s[index++])
            }

            return result.toString()
        }
    }

    expect {
        Solution().addSpaces(
            "", intArrayOf()
        )
    }
}