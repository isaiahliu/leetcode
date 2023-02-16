package p00xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun deleteDuplicates(head: ListNode?): ListNode? {
            var t = head

            while (t != null) {
                if (t.`val` == t.next?.`val`) {
                    t.next = t.next?.next
                } else {
                    t = t.next
                }
            }

            return head
        }
    }

    measureTimeMillis {
        Solution().deleteDuplicates(null)
    }.also { println("Time cost: ${it}ms") }
}

