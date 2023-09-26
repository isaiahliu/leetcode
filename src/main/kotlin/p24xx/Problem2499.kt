package p24xx

import util.expect
import util.input
import java.util.*

fun main() {
    class Solution {
        fun minimumTotalCost(nums1: IntArray, nums2: IntArray): Long {
            val counts = hashMapOf<Int, Int>()
            var minIndex = Int.MAX_VALUE
            var count = 0
            var result = 0L
            val diffIndices = LinkedList<Int>()

            nums1.indices.forEach {
                if (nums1[it] == nums2[it]) {
                    minIndex = minIndex.coerceAtMost(it)
                    counts[nums1[it]] = (counts[nums1[it]] ?: 0) + 1
                    count++
                    result += it
                } else {
                    diffIndices.add(it)
                }
            }

            return when {
                count == 0 -> 0
                counts.values.max() * 2L > count -> {
                    var (maxNum, maxCount) = counts.maxBy { it.value }
                    val otherHalf = count - maxCount

                    while (maxCount > otherHalf) {
                        diffIndices.pollFirst()?.also {
                            if (nums1[it] != maxNum && nums2[it] != maxNum) {
                                maxCount--
                                result += it
                            }
                        } ?: return -1
                    }

                    result
                }

                count % 2 == 0 -> result
                else -> result + (diffIndices.pollFirst()?.takeIf { it < minIndex } ?: minIndex)
            }
        }
    }

    expect {
        val (line1, line2) = input
        Solution().minimumTotalCost(
            line1.split(",").map { it.toInt() }.toIntArray(), line2.split(",").map { it.toInt() }.toIntArray()
        )
    }
}