package p01xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun sortList(head: ListNode?): ListNode? {
            head?.next ?: return head

            val root = ListNode(Int.MIN_VALUE)
            root.next = head

            var t = head.next
            head.next = null

            var previousT = root
            while (t != null) {
                val nextT = t.next

                var first = if (t.`val` >= previousT.`val`) previousT else root
                var second = first.next

                while (second != null && t.`val` >= second.`val`) {
                    first = second
                    second = second.next
                }

                first.next = t
                t.next = second

                previousT = t

                t = nextT
            }

            return root.next
        }
    }
    expect {
        Solution().sortList(
            null
        )
    }
}

