package p00xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun deleteDuplicates(head: ListNode?): ListNode? {
            var t = head

            while (t != null) {
                if (t.`val` == t.next?.`val`) {
                    t.next = t.next?.next
                } else {
                    t = t.next
                }
            }

            return head
        }
    }

    expect {
        Solution().deleteDuplicates(null)
    }
}

