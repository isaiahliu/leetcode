package p32xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(m: Int, n: Int, horizontalCut: IntArray, verticalCut: IntArray): Long {
            horizontalCut.sort()
            verticalCut.sort()

            var result = 0L

            var hIndex = horizontalCut.lastIndex
            var vIndex = verticalCut.lastIndex
            while (hIndex >= 0 || vIndex >= 0) {
                result += if (vIndex < 0 || (hIndex >= 0 && horizontalCut[hIndex] > verticalCut[vIndex])) {
                    horizontalCut[hIndex--] * (verticalCut.size - vIndex)
                } else {
                    verticalCut[vIndex--] * (horizontalCut.size - hIndex)
                }
            }
            return result
        }
    }

    expect(13) {
        Solution().minimumCost(
            3, 2,
            intArrayOf(1, 3),
            intArrayOf(5),
        )
    }

    expect(15) {
        Solution().minimumCost(
            2, 2,
            intArrayOf(7),
            intArrayOf(4),
        )
    }

    expect(28) {
        Solution().minimumCost(
            6, 3,
            intArrayOf(2, 3, 2, 3, 1),
            intArrayOf(1, 2),
        )
    }

    expect(258) {
        Solution().minimumCost(
            7, 4,
            intArrayOf(13, 6, 12, 14, 4, 7),
            intArrayOf(14, 15, 11),
        )
    }
}