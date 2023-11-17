package p27xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumSumQueries(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): IntArray {
            val num2SumMap = TreeMap<Int, Int>()

            val numIndices = nums1.indices.sortedByDescending { nums1[it] }

            var numIndex = 0

            val result = IntArray(queries.size) { -1 }

            queries.indices.sortedByDescending { queries[it][0] }.forEach { resultIndex ->
                val (query1, query2) = queries[resultIndex]

                while (numIndex < numIndices.size && nums1[numIndices[numIndex]] >= query1) {
                    val num2 = nums2[numIndices[numIndex]]

                    val sum = nums1[numIndices[numIndex++]] + num2

                    if (num2SumMap.ceilingEntry(num2)?.value?.takeIf { it >= sum } == null) {
                        while (true) {
                            num2SumMap.floorEntry(num2)?.takeIf { it.value <= sum }?.also {
                                num2SumMap.remove(it.key)
                            } ?: break
                        }

                        num2SumMap[num2] = sum
                    }
                }

                num2SumMap.ceilingEntry(query2)?.also {
                    result[resultIndex] = it.value
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumSumQueries(
            intArrayOf(), intArrayOf(), arrayOf()
        )
    }
}
