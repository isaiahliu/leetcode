package p04xx

import util.expect

fun main() {
    class Solution {
        fun countSegments(s: String): Int {
            var result = 0

            var wordLength = 0
            ("$s ").forEach {
                when (it) {
                    ' ' -> {
                        if (wordLength > 0) {
                            result++
                        }
                        wordLength = 0
                    }

                    else -> {
                        wordLength++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countSegments("")
    }
}


