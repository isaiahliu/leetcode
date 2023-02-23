package p02xx

import util.ListNode

fun main() {
    class Solution {
        fun removeElements(head: ListNode?, `val`: Int): ListNode? {
            val root = ListNode(`val` + 1)
            root.next = head

            var t: ListNode? = root
            while (t?.next != null) {
                if (t.next?.`val` == `val`) {
                    t.next = t.next?.next
                } else {
                    t = t.next
                }
            }

            return root.next
        }
    }

    println(
        Solution().removeElements(
            null, 1
        )
    )
}

