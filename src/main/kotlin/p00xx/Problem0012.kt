package p00xx

fun main() {
    class Solution {
        val pairs = arrayOf(
            "M" to "",
            "C" to "D",
            "X" to "L",
            "I" to "V"
        )

        fun intToRoman(num: Int): String {
            return buildString {
                num.toString().padStart(4, '0').map { it - '0' }.forEachIndexed { index, n ->
                    val (one, five) = pairs[index]
                    when (n) {
                        in 1..3 -> {
                            append(one.repeat(n))
                        }

                        in 4..8 -> {
                            append(one.repeat((5 - n).coerceAtLeast(0)))

                            append(five)

                            append(one.repeat((n - 5).coerceAtLeast(0)))
                        }

                        9 -> {
                            append(one)
                            append(pairs[index - 1].first)
                        }
                    }
                }
            }
        }
    }

    Solution().intToRoman(3)
    Solution().intToRoman(4)
    Solution().intToRoman(9)
    Solution().intToRoman(58)
    Solution().intToRoman(1994)
}

