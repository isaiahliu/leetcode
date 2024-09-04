package p31xx

import util.expect

fun main() {
    class Solution {
        fun clearDigits(s: String): String {
            return buildString {
                s.forEach {
                    when {
                        it !in '0'..'9' -> {
                            append(it)

                        }

                        isNotEmpty() -> {
                            deleteAt(lastIndex)
                        }
                    }
                }
            }
        }
    }

    expect {
        Solution().clearDigits(
            "a"
        )
    }
}
