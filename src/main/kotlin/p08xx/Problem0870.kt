package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun advantageCount(nums1: IntArray, nums2: IntArray): IntArray {
            val sortedTarget = LinkedList(nums2.indices.sortedBy { nums2[it] })

            val result = IntArray(nums1.size)

            nums1.sorted().forEach {
                if (it > nums2[sortedTarget.peek()]) {
                    result[sortedTarget.pollFirst()] = it
                } else {
                    result[sortedTarget.pollLast()] = it
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().advantageCount(
            intArrayOf(2, 0, 4, 1, 2), intArrayOf(1, 3, 0, 0, 2)
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}