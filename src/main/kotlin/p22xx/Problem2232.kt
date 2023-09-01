package p22xx

import util.expect

fun main() {
    class Solution {
        fun minimizeResult(expression: String): String {
            val (left, right) = expression.split("+")

            var min = left.toInt() + right.toInt()
            var result = "(${expression})"

            left.indices.forEach { l ->
                right.indices.forEach { r ->
                    val l1 = left.take(l)
                    val l2 = left.drop(l)

                    val r1 = right.take(r + 1)
                    val r2 = right.drop(r + 1)

                    ((l1.toIntOrNull() ?: 1) * (l2.toInt() + r1.toInt()) * (r2.toIntOrNull() ?: 1)).takeIf { it < min }
                        ?.also {
                            min = it
                            result = "${l1}(${l2}+${r1})${r2}"
                        }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimizeResult(
            "247+38"
        )
    }
}