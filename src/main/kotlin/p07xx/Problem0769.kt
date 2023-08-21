package p07xx

import util.expect

fun main() {
    class Solution {
        fun maxChunksToSorted(arr: IntArray): Int {
            var result = 0

            var targetIndex = -1
            arr.forEachIndexed { index, num ->
                targetIndex = targetIndex.coerceAtLeast(num)

                if (index == targetIndex) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().maxChunksToSorted(
            intArrayOf(1, 0, 2, 3, 4)
        )
    }
}