package p08xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numComponents(head: ListNode?, nums: IntArray): Int {
            val numSet = nums.toSet()

            var t = head

            var result = 0
            var count = 0
            while (t != null) {
                if (t.`val` in numSet) {
                    count = 1
                } else {
                    result += count
                    count = 0
                }

                t = t.next
            }

            result += count

            return result
        }
    }

    measureTimeMillis {
        Solution().numComponents(
            null, intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}