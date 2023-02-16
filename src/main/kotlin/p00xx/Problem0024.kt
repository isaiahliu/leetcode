package p00xx

import util.ListNode

fun main() {
    class Solution {
        fun swapPairs(head: ListNode?): ListNode? {
            return head?.next?.let {
                head.next = swapPairs(it.next)
                it.next = head
                it
            } ?: head
        }
    }
}

