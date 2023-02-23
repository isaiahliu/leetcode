package p02xx

import util.ListNode

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

    println(
        Solution().reverseList(
            null
        )
    )
}

