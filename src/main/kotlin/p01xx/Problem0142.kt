package p01xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun detectCycle(head: ListNode?): ListNode? {
            var slow = head?.next
            var fast = head?.next?.next

            var slowStep = 1
            while (slow != null && fast != null) {
                if (slow == fast) {
                    slow = head

                    while (slow != fast) {
                        slow = slow?.next
                        fast = fast?.next
                    }

                    return slow
                }

                slow = slow.next
                fast = fast.next?.next
                slowStep++
            }

            return null
        }
    }

    expect {
        val n1 = ListNode(0)
        val n2 = ListNode(1)
        n1.next = n2
        n2.next = n1
        Solution().detectCycle(
            n1
        )
    }
}

