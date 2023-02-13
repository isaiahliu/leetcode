package p00xx

fun main() {
    class Solution {
        fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
            val lines = arrayListOf<MutableList<String>>()
            val spaces = arrayListOf<Int>()

            var lastLine: MutableList<String> = arrayListOf()
            var totalWidth = -1
            var lastLineSpace = -1

            words.forEach { word ->
                if (totalWidth + word.length + 1 > maxWidth) {
                    lines.add(lastLine)
                    spaces.add(maxWidth - totalWidth + lastLineSpace)
                    lastLine = arrayListOf()
                    totalWidth = -1
                    lastLineSpace = -1
                }

                lastLine.add(word)
                totalWidth += word.length + 1
                lastLineSpace++
            }

            val result = arrayListOf<String>()
            val str = StringBuilder()

            lines.forEachIndexed { lineIndex, line ->
                val space = spaces[lineIndex]
                if (line.size == 1) {
                    str.append(line[0])
                    str.append(" ".repeat(maxWidth - str.length))
                } else {
                    line.forEachIndexed { wordIndex, word ->
                        if (wordIndex > 0) {
                            str.append(" ".repeat((space / (line.size - 1)) + if (wordIndex <= space % (line.size - 1)) 1 else 0))
                        }

                        str.append(word)
                    }
                }

                result += str.toString()
                str.clear()
            }

            if (lastLine.isNotEmpty()) {
                str.append(lastLine.joinToString(" "))
                str.append(" ".repeat(maxWidth - str.length))
                result += str.toString()
            }

            return result
        }
    }


    println(
        Solution().fullJustify(
            arrayOf(
                "Science",
                "is",
                "what",
                "we",
                "understand",
                "well",
                "enough",
                "to",
                "explain",
                "to",
                "a",
                "computer.",
                "Art",
                "is",
                "everything",
                "else",
                "we",
                "do"
            ), 20
        ).joinToString("\n")
    )
}

