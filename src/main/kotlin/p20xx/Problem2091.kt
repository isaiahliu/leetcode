package p20xx

import util.expect

fun main() {
    class Solution {
        fun minimumDeletions(nums: IntArray): Int {
            var minIndex = -1
            var maxIndex = -1

            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE
            nums.forEachIndexed { index, i ->
                if (i < min) {
                    min = i
                    minIndex = index
                }

                if (i > max) {
                    max = i
                    maxIndex = index
                }
            }

            val indices = setOf(minIndex, maxIndex).sorted()

            return if (indices.size == 1) {
                (indices[0] + 1).coerceAtMost(nums.lastIndex - indices[0] + 1)
            } else {
                (indices[0] + 1 + nums.lastIndex - indices[1] + 1).coerceAtMost(indices[1] + 1)
                    .coerceAtMost(nums.lastIndex - indices[0] + 1)
            }
        }
    }

    expect {
        Solution().minimumDeletions(
            intArrayOf(7, 4, 3, 9, 1, 8, 5, 2, 6)
        )
    }
}