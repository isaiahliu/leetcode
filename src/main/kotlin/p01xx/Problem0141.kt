package p01xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun hasCycle(head: ListNode?): Boolean {
            var slow = head?.next
            var fast = head?.next?.next

            while (slow != null && fast != null) {
                if (slow == fast) {
                    return true
                }

                slow = slow.next
                fast = fast.next?.next
            }

            return false
        }
    }

    expect {
        Solution().hasCycle(
            null
        )
    }
}

