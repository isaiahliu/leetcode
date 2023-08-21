package p03xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun oddEvenList(head: ListNode?): ListNode? {
            val oddRoot = ListNode(1)
            val evenRoot = ListNode(0)

            var oddTail = oddRoot
            var evenTail = evenRoot

            var t = head
            var n = 1
            while (t != null) {
                if (n % 2 == 0) {
                    evenTail.next = t
                    evenTail = t
                } else {
                    oddTail.next = t
                    oddTail = t
                }

                t = t.next
                n++
            }

            oddTail.next = evenRoot.next
            evenTail.next = null

            return oddRoot.next
        }
    }

    expect {
        Solution().oddEvenList(null)
    }
}

