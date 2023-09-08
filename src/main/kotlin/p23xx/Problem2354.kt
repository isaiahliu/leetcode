package p23xx

import util.expect

fun main() {
    class Solution {
        fun countExcellentPairs(nums: IntArray, k: Int): Long {
            val counts = LongArray(31)

            val visited = hashSetOf<Int>()
            nums.forEach {
                if (visited.add(it)) {
                    counts[it.countOneBits()]++
                }
            }

            for (i in 1 until counts.size) {
                counts[i] += counts[i - 1]
            }

            var result = 0L

            for (size1 in counts.lastIndex downTo 0) {
                val size1Count = (counts[size1] - (counts.getOrNull(size1 - 1) ?: 0)).takeIf { it > 0 } ?: continue
                if (size1 * 2 >= k) {
                    result += size1Count * size1Count
                }

                val size2 = (k - size1).takeIf { it < size1 } ?: continue

                val size2Count = counts[size1 - 1] - (counts.getOrNull(size2 - 1) ?: 0)

                result += size1Count * size2Count * 2
            }

            return result
        }
    }

    expect {
        Solution().countExcellentPairs(
            intArrayOf(1, 2, 3, 1), 3
        )
    }
}