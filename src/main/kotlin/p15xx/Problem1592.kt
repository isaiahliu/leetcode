package p15xx

import util.expect

fun main() {
    class Solution {
        fun reorderSpaces(text: String): String {
            val word = StringBuilder()
            val words = arrayListOf<String>()
            var blankCount = 0

            text.forEach {
                when (it) {
                    ' ' -> {
                        blankCount++
                        if (word.isNotEmpty()) {
                            words.add(word.toString())
                        }
                        word.clear()
                    }

                    else -> {
                        word.append(it)
                    }
                }
            }

            if (word.isNotEmpty()) {
                words.add(word.toString())
            }

            return if (words.size <= 1) {
                words.firstOrNull().orEmpty() + " ".repeat(blankCount)
            } else {
                words.joinToString(" ".repeat(blankCount / (words.size - 1))) + " ".repeat(blankCount % (words.size - 1))
            }
        }
    }

    expect {
        Solution().reorderSpaces(
            ""
        )
    }
}

