package p01xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun hasCycle(head: ListNode?): Boolean {
            var slow = head?.next
            var fast = head?.next?.next

            while (slow != null && fast != null) {
                if (slow == fast) {
                    return true
                }

                slow = slow.next
                fast = fast.next?.next
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().hasCycle(
            null
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

