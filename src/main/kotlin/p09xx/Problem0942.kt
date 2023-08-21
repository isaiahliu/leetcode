package p09xx

import util.expect

fun main() {
class Solution {
    fun diStringMatch(s: String): IntArray {
        val result = IntArray(s.length + 1)

        var index = 0

        var start = s.length
        var length = 1

        s.forEach {
            when (it) {
                'I' -> {
                    start--
                    length++
                }

                'D' -> {
                    repeat(length) { l ->
                        result[index++] = start + l
                    }

                    start--
                    length = 1
                }
            }
        }

        if (index < result.size) {
            repeat(length) { l ->
                result[index++] = start + l
            }
        }

        return result
    }
}

    expect {
        Solution().diStringMatch(
            "IDID"
        ).toList()
    }
}