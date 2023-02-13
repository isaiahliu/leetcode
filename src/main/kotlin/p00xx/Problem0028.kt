package p00xx

fun main() {
    class Solution {
        fun strStr(haystack: String, needle: String): Int {
            var result = -1

            loop@ for (i in 0..haystack.length - needle.length) {
                for ((j, c) in needle.withIndex()) {
                    if (haystack[i + j] != c) {
                        continue@loop
                    }
                }

                result = i
                break
            }

            return result
        }
    }
}

