package p03xx

import util.expect

fun main() {
    class Solution {
        fun lexicalOrder(n: Int): List<Int> {
            val result = arrayListOf<Int>()

            fun walk(base: Int) {
                for (i in 0..9) {
                    val newNum = base + i

                    if (newNum > 0) {
                        if (newNum > n) {
                            break
                        }

                        result.add(newNum)

                        walk(newNum * 10)
                    }
                }
            }

            walk(0)

            return result
        }
    }

    expect {
        Solution().lexicalOrder(
            13
        )
    }
}

