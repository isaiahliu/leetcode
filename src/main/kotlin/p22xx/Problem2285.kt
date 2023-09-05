package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumImportance(n: Int, roads: Array<IntArray>): Long {
            val degrees = IntArray(n)

            roads.forEach { (from, to) ->
                degrees[from]++
                degrees[to]++
            }

            var result = 0L

            degrees.indices.sortedBy { degrees[it] }.forEachIndexed { index, di ->
                result += (index + 1) * degrees[di]
            }

            return result
        }
    }

    expect {
        Solution().maximumImportance(
            5, arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(0, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 4)
            )
        )
    }
}