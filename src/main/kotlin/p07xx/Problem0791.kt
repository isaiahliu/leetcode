package p07xx

import util.expect

fun main() {
    class Solution {
        fun customSortString(order: String, s: String): String {
            val orderMap = order.mapIndexed { index, c -> c to index }.toMap()

            return s.toCharArray().sortedWith(compareBy { orderMap[it] ?: 0 }).toCharArray().let { String(it) }
        }
    }

    expect {
        Solution().customSortString(
            "", ""
        )
    }
}