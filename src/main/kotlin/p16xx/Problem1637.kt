package p16xx

import util.expect

fun main() {
    class Solution {
        fun maxWidthOfVerticalArea(points: Array<IntArray>): Int {
            var result = 0
            points.map { it[0] }.toSortedSet().reduce { a, b ->
                result = result.coerceAtLeast(b - a)
                b
            }

            return result
        }
    }

    expect {
        Solution().maxWidthOfVerticalArea(
            arrayOf()

        )

    }
}