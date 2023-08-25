package p20xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun deleteMiddle(head: ListNode?): ListNode? {
            head?.next ?: return null
            var slow = head
            var fast = head.next?.next?.next

            while (fast != null) {
                fast = fast.next?.next
                slow = slow?.next
            }

            slow?.next = slow?.next?.next

            return head
        }
    }

    expect {
        Solution().deleteMiddle(
            null
        )
    }
}