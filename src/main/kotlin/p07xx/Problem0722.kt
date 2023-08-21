package p07xx

import util.expect

fun main() {
    class Solution {
        fun removeComments(source: Array<String>): List<String> {
            val result = arrayListOf<String>()

            val str = StringBuilder()
            var inBlockComment = false

            loop@ for (line in source) {
                var index = 0
                while (index < line.length) {
                    val c = line[index]
                    val next = line.getOrNull(index + 1)
                    when {
                        inBlockComment && c == '*' && next == '/' -> {
                            index++
                            inBlockComment = false
                        }

                        inBlockComment -> {
                        }

                        c == '/' && next == '/' -> {
                            break
                        }

                        c == '/' && next == '*' -> {
                            inBlockComment = true
                            index++
                        }

                        else -> {
                            str.append(c)
                        }
                    }

                    index++
                }

                if (!inBlockComment) {
                    if (str.isNotEmpty()) {
                        result.add(str.toString())
                        str.clear()
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().removeComments(
            arrayOf(
                "main() {",
                "  Node* p;",
                "  /* declare a Node",
                "  /*float f = 2.0",
                "   p->val = f;",
                "   /**/",
                "   p->val = 1;",
                "   //*/ cout << success;*/",
                "}",
                " "
            )
        ).joinToString("\n")
    }
}