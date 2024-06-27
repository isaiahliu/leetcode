package p27xx

import util.expect

fun main() {
    class Solution {
        fun smallestString(s: String): String {
            var step = 0
            return buildString {
                s.forEach {
                    when (step) {
                        0 -> {
                            if (it > 'a') {
                                step++
                            }
                        }

                        1 -> {
                            if (it == 'a') {
                                step++
                            }
                        }
                    }

                    append(it - (step % 2))
                }
            }.takeIf { step > 0 } ?: ("a".repeat(s.length - 1) + "z")
        }
    }

    expect(119) {
        Solution().smallestString(
            ""
        )
    }
}
