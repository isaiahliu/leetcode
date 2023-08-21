package p19xx

import util.expect

fun main() {
    class Solution {
        fun minDifference(nums: IntArray, queries: Array<IntArray>): IntArray {
            val counts = Array(nums.size) {
                IntArray(100)
            }
            counts[0][nums[0] - 1]++

            for (index in 1 until nums.size) {
                repeat(100) {
                    counts[index][it] = counts[index - 1][it]
                }

                counts[index][nums[index] - 1]++
            }

            return queries.map { (from, to) ->
                val rightCount = counts[to]
                val leftCount = counts.getOrNull(from - 1) ?: IntArray(100)

                var result = Int.MAX_VALUE

                var pre = -1
                for (i in 0 until 100) {
                    if (rightCount[i] > leftCount[i]) {
                        if (pre >= 0) {
                            result = result.coerceAtMost(i - pre)
                        }

                        pre = i
                    }
                }


                result.takeIf { it < Int.MAX_VALUE } ?: -1
            }.toIntArray()
        }
    }

    expect {
        Solution().minDifference(
            intArrayOf(1, 3, 4, 8), arrayOf(intArrayOf(0, 1))
        ).toList()
    }
}
