package p18xx

import util.expect

fun main() {
    class Solution {
        fun findingUsersActiveMinutes(logs: Array<IntArray>, k: Int): IntArray {
            val result = IntArray(k)

            logs.groupBy { it[0] }.values.forEach {
                it.map { it[1] }.distinct().size.takeIf { it > 0 }?.also {
                    result[it - 1]++
                }
            }

            return result
        }
    }

    expect {
        Solution().findingUsersActiveMinutes(
            arrayOf(), 4
        )
    }
}
