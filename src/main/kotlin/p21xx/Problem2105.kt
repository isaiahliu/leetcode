package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimumRefill(plants: IntArray, capacityA: Int, capacityB: Int): Int {
            var remainA = capacityA
            var remainB = capacityB

            var leftIndex = 0
            var rightIndex = plants.lastIndex

            var result = 0

            while (leftIndex < rightIndex) {
                if (remainA < plants[leftIndex]) {
                    remainA = capacityA
                    result++
                }

                remainA -= plants[leftIndex++]

                if (remainB < plants[rightIndex]) {
                    remainB = capacityB
                    result++
                }

                remainB -= plants[rightIndex--]
            }

            if (leftIndex == rightIndex) {
                if (remainA.coerceAtLeast(remainB) < plants[leftIndex]) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumRefill(
            intArrayOf(4, -2, -3, 4, 1), 1, 2
        )
    }
}