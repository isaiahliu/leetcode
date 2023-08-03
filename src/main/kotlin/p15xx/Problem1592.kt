package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reorderSpaces(text: String): String {
            val stringBuilder = StringBuilder()
            val words = arrayListOf<String>()
            var blankCount = 0

            text.forEach {
                when (it) {
                    ' ' -> {
                        blankCount++
                        if (stringBuilder.isNotEmpty()) {
                            words.add(stringBuilder.toString())
                        }
                        stringBuilder.clear()
                    }

                    else -> {
                        stringBuilder.append(it)
                    }
                }
            }

            if (stringBuilder.isNotEmpty()) {
                words.add(stringBuilder.toString())
            }

            return if (words.size <= 1) {
                words.firstOrNull().orEmpty() + " ".repeat(blankCount)
            } else {
                words.joinToString(" ".repeat(blankCount / (words.size - 1))) + " ".repeat(blankCount % (words.size - 1))
            }
        }
    }

    measureTimeMillis {
        Solution().reorderSpaces(
            ""
        ).also { println("${it} should be ${it}") }
    }
}

