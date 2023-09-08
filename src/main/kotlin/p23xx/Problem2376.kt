package p23xx

import util.expect

fun main() {
    class Solution {
        fun countSpecialNumbers(n: Int): Int {
            fun fullSize(length: Int, availableNums: Int = 9): Int {
                var result = 1

                var r = availableNums

                repeat(length) {
                    result *= r--
                }

                return result
            }

            val str = n.toString()

            var result = (1 until str.length).sumOf {
                fullSize(it - 1) * 9
            }

            fun dfs(used: Set<Int>) {
                if (used.size < str.length) {
                    val nextNum = str[used.size] - '0'

                    (0 until nextNum).forEach {
                        if (it !in used) {
                            result += fullSize(str.length - used.size - 1, 9 - used.size)
                        }
                    }

                    if (nextNum !in used) {
                        dfs(used + nextNum)
                    }
                } else {
                    result++
                }
            }

            result += fullSize(str.length - 1, 9) * (str[0] - '1')

            dfs(setOf(str[0] - '0'))

            return result
        }
    }

    expect {
        Solution().countSpecialNumbers(
            20
        )
    }
}