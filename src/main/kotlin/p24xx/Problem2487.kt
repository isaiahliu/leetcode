package p24xx

import util.ListNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun removeNodes(head: ListNode?): ListNode? {
            val stack = LinkedList<ListNode>()

            var t = head
            while (t != null) {
                while (stack.isNotEmpty() && stack.peekLast().`val` < t.`val`) {
                    stack.pollLast()
                }

                stack.add(t)

                t = t.next
            }

            stack.reduce { acc, listNode ->
                acc.next = listNode
                listNode
            }

            stack.peekLast()?.next = null

            return stack.peek()
        }
    }

    expect {
        Solution().removeNodes(
            null
        )
    }
}