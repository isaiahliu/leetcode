package p00xx

fun main() {
    class Solution {
        fun generateParenthesis(n: Int): List<String> {
            val result = arrayListOf<String>()

            if (n == 0) {
                return listOf("")
            }

            if (n == 1) {
                return listOf("()")
            }

            repeat(n) {
                result += generateParenthesis(it).map { left ->
                    generateParenthesis(n - it - 1).map { right ->
                        "(${left})${right}"
                    }
                }.flatten()
            }

            return result
        }
    }

    println(Solution().generateParenthesis(3))
}

