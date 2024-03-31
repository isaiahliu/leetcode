package p28xx

import util.expect

fun main() {
    class Solution {
        fun finalString(s: String): String {
            var forward = true

            val str = buildString {
                s.forEach {
                    when {
                        it == 'i' -> {
                            forward = !forward
                        }

                        forward -> {
                            append(it)
                        }

                        else -> {
                            insert(0, it)
                        }
                    }
                }
            }

            return if (forward) str else str.reversed()
        }
    }

    expect {
        Solution().finalString(
            ""
        )
    }
}
