package p03xx

import util.expect

fun main() {
    class Solution {
        fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val counts = hashMapOf<Int, Int>()

            nums.forEach {
                counts[it] = (counts[it] ?: 0) + 1
            }

            return counts.entries.sortedByDescending { it.value }.take(k).map { it.key }.toIntArray()
        }
    }

    expect {
        Solution().topKFrequent(
            intArrayOf(), 1
        )
    }
}

