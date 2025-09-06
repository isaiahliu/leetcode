package p34xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(queries: Array<IntArray>): Long {
            return queries.sumOf {
                var result = 0L
                var left = it[0]
                val right = it[1] + 1

                for (op in 1..15) {
                    val l = 1 shl ((op - 1) * 2)
                    val r = 1 shl (op * 2)

                    when {
                        l > right -> {
                            break
                        }

                        left < r -> {
                            result += (minOf(r, right) - left) * op.toLong()
                            left = r
                        }
                    }
                }

                (result + 1) / 2
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
