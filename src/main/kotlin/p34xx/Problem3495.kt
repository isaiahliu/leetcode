package p34xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(queries: Array<IntArray>): Long {
            return queries.sumOf { (left, right) ->
                (1..15).sumOf { op ->
                    maxOf(((minOf(1 shl (op * 2), right + 1) - maxOf(1 shl ((op - 1) * 2), left)) * op.toLong()), 0L)
                }.let { (it + 1) / 2 }
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
