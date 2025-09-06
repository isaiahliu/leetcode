package p34xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(queries: Array<IntArray>): Long {
            class SegNode(val l4: Int, val r4: Int) {
                private val l = 1L shl (l4 * 2)
                private val r = (1L shl ((r4 + 1) * 2)) - 1

                val children by lazy {
                    arrayOf(SegNode(l4, (l4 + r4) / 2), SegNode((l4 + r4) / 2 + 1, r4))
                }

                fun query(num1: Int, num2: Int): Long {
                    return if (num1 > r || num2 < l) {
                        0L
                    } else if (l4 < r4) {
                        children.sumOf { it.query(num1, num2) }
                    } else {
                        (minOf(num2.toLong(), r) - maxOf(num1.toLong(), l) + 1L) * (l4 + 1)
                    }
                }
            }

            val root = SegNode(0, 15)

            return queries.sumOf {
                (root.query(it[0], it[1]) + 1) / 2
            }
        }
    }

    expect {
        Solution().minOperations(
            arrayOf(
                intArrayOf(1, 2)
            )
        )
    }
}
