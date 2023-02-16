package p00xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun partition(head: ListNode?, x: Int): ListNode? {
            val leftHead = ListNode(Int.MAX_VALUE)
            val rightHead = ListNode(Int.MAX_VALUE)

            var leftTail: ListNode = leftHead
            var rightTail: ListNode = rightHead

            var t = head
            while (t != null) {
                if (t.`val` < x) {
                    leftTail.next = t
                    leftTail = t
                } else {
                    rightTail.next = t
                    rightTail = t
                }

                t = t.next
            }

            leftTail.next = rightHead.next
            rightTail.next = null

            return leftHead.next
        }
    }

    measureTimeMillis {
        Solution().partition(null, 0).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

