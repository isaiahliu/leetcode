package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestValsFromLabels(values: IntArray, labels: IntArray, numWanted: Int, useLimit: Int): Int {
            val sortedIndices = values.indices.sortedWith(compareByDescending { values[it] }).iterator()

            var count = 0

            val used = hashMapOf<Int, Int>()
            var result = 0
            while (count < numWanted && sortedIndices.hasNext()) {
                val i = sortedIndices.next()

                val label = labels[i]

                val labelUsedCount = used[label] ?: 0
                if (labelUsedCount < useLimit) {
                    count++
                    result += values[i]
                    used[label] = labelUsedCount + 1
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().largestValsFromLabels(
            intArrayOf(5, 4, 3, 2, 1), intArrayOf(1, 1, 2, 2, 3), 3, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
