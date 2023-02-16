package p00xx

import util.ListNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rotateRight(head: ListNode?, k: Int): ListNode? {
            if (head == null || k == 0) {
                return head
            }

            var t = head

            val stack = LinkedList<ListNode>()

            while (t != null) {
                stack.push(t)
                t = t.next
            }

            val popCount = k % stack.size

            if (popCount == 0) {
                return head
            }

            stack.peek().next = head

            var top = head
            repeat(popCount) {
                top = stack.pop()
            }

            stack.peek().next = null

            return top
        }
    }

    measureTimeMillis {
        println(Solution().rotateRight(null, 1))
    }.also { println("Time cost: ${it}ms") }
}

