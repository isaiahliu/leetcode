package p00xx

import util.ListNode
import java.util.*

fun main() {
    class Solution {
        fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
            if (head == null || k == 1) {
                return head
            }

            val stack = LinkedList<ListNode>()

            var current = head

            repeat(k) {
                stack.push(current ?: return head)

                current = current?.next
            }

            val nextLink = reverseKGroup(stack.peek().next, k)

            stack.reduce { left, right ->
                left.next = right
                right
            }.next = nextLink

            return stack.peek()
        }
    }

    Solution().reverseKGroup(ListNode(1).also {
        it.next = ListNode(2).also {
            it.next = ListNode(3).also {
                it.next = ListNode(4).also {
                    it.next = ListNode(5)
                }
            }
        }
    }, 2)
}

