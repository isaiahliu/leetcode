package p08xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun middleNode(head: ListNode?): ListNode? {
            var slow = head
            var fast = head?.next
            while (fast != null) {
                slow = slow?.next
                fast = fast.next?.next
            }

            return slow
        }
    }

    measureTimeMillis {
        Solution().middleNode(
            null
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}