fun main() {
    class Solution {
        val map = mapOf(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz"
        )

        fun letterCombinations(digits: String): List<String> {
            return when (digits.length) {
                0 -> {
                    emptyList()
                }

                1 -> {
                    map[digits[0]]?.map { it.toString() }.orEmpty()
                }

                else -> {
                    val sub = digits.substring(1)

                    map[digits[0]]?.map { c ->
                        letterCombinations(sub).map { c + it }
                    }?.flatten().orEmpty()
                }
            }
        }
    }
}

