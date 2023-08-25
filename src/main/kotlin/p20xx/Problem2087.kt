package p20xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minCost(startPos: IntArray, homePos: IntArray, rowCosts: IntArray, colCosts: IntArray): Int {
            val deltaRow = (homePos[0] - startPos[0]).sign
            val deltaColumn = (homePos[1] - startPos[1]).sign

            var result = 0

            var (row, column) = startPos

            if (deltaRow != 0) {
                while (row != homePos[0]) {
                    row += deltaRow
                    result += rowCosts[row]
                }
            }

            if (deltaColumn != 0) {
                while (column != homePos[1]) {
                    column += deltaColumn
                    result += colCosts[column]
                }
            }

            return result
        }
    }

    expect {
        Solution().minCost(
            intArrayOf(),
            intArrayOf(),
            intArrayOf(),
            intArrayOf()
        )
    }
}