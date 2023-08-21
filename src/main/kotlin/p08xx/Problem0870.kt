package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun advantageCount(nums1: IntArray, nums2: IntArray): IntArray {
            val sortedTarget = LinkedList(nums2.indices.sortedBy { nums2[it] })

            val result = IntArray(nums1.size)

            nums1.sorted().forEach {
                if (it > nums2[sortedTarget.peek()]) {
                    sortedTarget.pollFirst()
                } else {
                    sortedTarget.pollLast()
                }.also { index ->
                    result[index] = it
                }
            }

            return result
        }
    }

    expect {
        Solution().advantageCount(
            intArrayOf(2, 0, 4, 1, 2), intArrayOf(1, 3, 0, 0, 2)
        )

    }
}