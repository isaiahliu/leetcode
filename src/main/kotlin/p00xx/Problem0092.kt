package p00xx

import util.ListNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
            if (left == right) {
                return head
            }

            val root = ListNode(Int.MAX_VALUE)
            root.next = head

            var tail: ListNode? = root
            repeat(left - 1) {
                tail = tail?.next
            }

            var t = tail?.next
            val stack = LinkedList<ListNode>()
            repeat(right - left + 1) {
                stack.push(t)

                t = t?.next
            }

            while (stack.isNotEmpty()) {
                tail?.next = stack.pop()
                tail = tail?.next
            }

            tail?.next = t

            return root.next
        }
    }

    measureTimeMillis {
        Solution().reverseBetween(
            ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 2, 4
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

