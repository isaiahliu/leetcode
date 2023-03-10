package p04xx

import util.ListNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            val stack1 = LinkedList<Int>()
            val stack2 = LinkedList<Int>()

            var t = l1
            while (t != null) {
                stack1.push(t.`val`)
                t = t.next
            }

            t = l2
            while (t != null) {
                stack2.push(t.`val`)
                t = t.next
            }

            var head: ListNode? = null

            var add = 0
            while (stack1.isNotEmpty() || stack2.isNotEmpty()) {
                val sum = (stack1.poll() ?: 0) + (stack2.poll() ?: 0) + add

                val newHead = ListNode(sum % 10)
                newHead.next = head
                head = newHead

                add = sum / 10
            }

            if (add > 0) {
                val newHead = ListNode(add)
                newHead.next = head
                head = newHead
            }

            return head
        }
    }

    measureTimeMillis {
        Solution().addTwoNumbers(
            null, null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


