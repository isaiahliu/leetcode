package p21xx

import util.expect

fun main() {
    class Solution {
        fun canBeValid(s: String, locked: String): Boolean {
            if (s.length % 2 == 1) {
                return false
            }

            arrayOf(s.indices to '(', s.indices.reversed() to ')').forEach { (indices, left) ->
                var depth = 0
                indices.forEach {
                    val c = s[it]
                    val l = locked[it] == '1'
                    when {
                        c == left -> depth++
                        l -> depth--
                        else -> depth++
                    }

                    if (depth < 0) {
                        return false
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().canBeValid(
            "))()))", "0101011"
        )
    }
}