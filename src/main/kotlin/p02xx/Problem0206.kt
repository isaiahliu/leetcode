package p02xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun reverseList(head: ListNode?): ListNode? {
            head?.next ?: return head

            val next = head.next
            head.next = null

            val reversedList = reverseList(next)

            next?.next = head

            return reversedList
        }
    }

    expect {
        Solution().reverseList(
            null
        )
    }
}

