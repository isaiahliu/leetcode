package p20xx

import util.ListNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun reverseEvenLengthGroups(head: ListNode?): ListNode? {
            var lastTail = head ?: return null
            var subSize = 2
            var cur = head.next
            while (cur != null) {
                var size = 0

                val list = LinkedList<ListNode>()

                while (cur != null && size < subSize) {
                    list.push(cur)
                    cur = cur.next
                    size++
                }

                if (list.size % 2 == 0) {
                    list.forEach {
                        lastTail.next = it
                        lastTail = it
                    }

                    lastTail.next = cur
                } else {
                    lastTail = list.peek()
                }

                subSize++
            }

            return head
        }
    }

    expect(1) {
        Solution().reverseEvenLengthGroups(
            ListNode(1, ListNode(1, ListNode(0, ListNode(6, ListNode(5)))))
        )
    }
}