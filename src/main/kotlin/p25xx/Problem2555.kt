package p25xx

import util.expect

fun main() {
    class Solution {
        fun maximizeWin(prizePositions: IntArray, k: Int): Int {
            val leftMax = IntArray(prizePositions.size + 1)
            val rightMax = IntArray(prizePositions.size + 1)

            var left = 0
            var max = 0
            (1..prizePositions.size).forEach { index ->
                if (prizePositions[index - 1] != prizePositions.getOrNull(index)) {
                    while (prizePositions[index - 1] - prizePositions[left] > k) {
                        left++
                    }

                    max = maxOf(max, index - left)

                    leftMax[index] = max
                }
            }

            var right = prizePositions.lastIndex
            max = 0
            (prizePositions.lastIndex downTo 0).forEach { index ->
                if (prizePositions.getOrNull(index - 1) != prizePositions[index]) {
                    while (prizePositions[right] - prizePositions[index] > k) {
                        right--
                    }

                    max = maxOf(max, right - index + 1)

                    rightMax[index] = max
                }
            }

            var result = 0
            leftMax.indices.forEach { index ->
                result = maxOf(result, leftMax[index] + rightMax[index])
            }

            return result
        }
    }

    expect {
        Solution().maximizeWin(
            intArrayOf(1, 3, 5), 2
        )
    }
}