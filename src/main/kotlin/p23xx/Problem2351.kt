package p23xx

import util.expect

fun main() {
    class Solution {
        fun repeatedCharacter(s: String): Char {
            val visited = hashSetOf<Char>()

            s.forEach {
                if (!visited.add(it)) {
                    return it
                }
            }

            return ' '
        }
    }

    expect {
        Solution().repeatedCharacter(
            ""
        )
    }
}