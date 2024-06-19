package p27xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxIncreasingCells(mat: Array<IntArray>): Int {
            val rowDp = Array(mat.size) { TreeMap<Int, Int>() }
            val columnDp = Array(mat[0].size) { TreeMap<Int, Int>() }

            var result = 0

            mat.indices.map { r ->
                mat[r].indices.map { c -> r to c }
            }.flatten().sortedBy { mat[it.first][it.second] }.forEach { (r, c) ->
                val num = mat[r][c]

                val max = maxOf(rowDp[r].lowerEntry(num)?.value ?: 0, columnDp[c].lowerEntry(num)?.value ?: 0) + 1

                rowDp[r][num] = maxOf(rowDp[r][num] ?: 0, max)
                columnDp[c][num] = maxOf(columnDp[c][num] ?: 0, max)

                result = maxOf(result, max)
            }

            return result
        }
    }

    expect {
        Solution().maxIncreasingCells(
            arrayOf(intArrayOf(3, 1), intArrayOf(3, 4))
        )
    }
}
