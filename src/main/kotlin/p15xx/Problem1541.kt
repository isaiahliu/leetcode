package p15xx

import util.expect

fun main() {
    class Solution {
        fun minInsertions(s: String): Int {
            var result = 0

            var leftCount = 0

            var index = 0
            while (index < s.length) {
                when (s[index]) {
                    '(' -> {
                        leftCount++
                    }

                    ')' -> {
                        if (s.getOrNull(index + 1) == ')') {
                            index++
                        } else {
                            result++
                        }

                        if (leftCount > 0) {
                            leftCount--
                        } else {
                            result++
                        }
                    }
                }

                index++
            }

            result += leftCount * 2

            return result
        }
    }

    expect {
        Solution().minInsertions(
            "{{}}}"
        )
    }
}

