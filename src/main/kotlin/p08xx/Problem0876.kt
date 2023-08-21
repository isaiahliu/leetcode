package p08xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun middleNode(head: ListNode?): ListNode? {
            var slow = head
            var fast = head?.next
            while (fast != null) {
                slow = slow?.next
                fast = fast.next?.next
            }

            return slow
        }
    }

    expect {
        Solution().middleNode(
            null
        )

    }
}