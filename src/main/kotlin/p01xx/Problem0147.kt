package p01xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun insertionSortList(head: ListNode?): ListNode? {
            head?.next ?: return head

            val root = ListNode(Int.MIN_VALUE)
            root.next = head

            var t = head.next
            head.next = null

            while (t != null) {
                val nextT = t.next

                var first = root
                var second = root.next

                while (second != null && t.`val` >= second.`val`) {
                    first = second
                    second = second.next
                }

                first.next = t
                t.next = second

                t = nextT
            }

            return root.next
        }
    }
    measureTimeMillis {
        Solution().insertionSortList(
            null
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

