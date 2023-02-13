package p00xx

fun main() {
    class Solution {
        fun convert(s: String, numRows: Int): String {
            if (numRows == 1) {
                return s
            }

            val numsList = arrayListOf(intArrayOf(0))

            repeat(numRows - 2) {
                numsList += intArrayOf(it + 1, numRows * 2 - it - 3)
            }

            numsList += intArrayOf(numRows - 1)

            val step = numRows * 2 - 2
            return buildString {
                numsList.forEach { nums ->
                    var index = 0
                    while (true) {
                        val chars = nums.map {
                            it + step * index
                        }.mapNotNull {
                            s.getOrNull(it)
                        }

                        if (chars.isEmpty()) {
                            break
                        } else {
                            chars.forEach { append(it) }

                            index++
                        }
                    }
                }
            }
        }
    }

    println(Solution().convert("PAYPALISHIRING", 3))
}

