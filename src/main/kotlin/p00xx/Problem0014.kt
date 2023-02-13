package p00xx

fun main() {
    class Solution {
        fun longestCommonPrefix(strs: Array<String>): String {
            return buildString {
                var index = 0

                found@ while (true) {
                    val c = strs[0].getOrNull(index) ?: break@found

                    for (i in 1 until strs.size) {
                        strs[i].getOrNull(index)?.takeIf { it == c } ?: break@found
                    }

                    append(c)

                    index++
                }
            }
        }
    }
}

