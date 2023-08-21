package p02xx

import util.ListNode
import util.expect

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

        fun isPalindrome(head: ListNode?): Boolean {
            head?.next ?: return true

            var slow = head
            var fast = head

            while (fast != null) {
                slow = slow?.next
                fast = fast.next?.next
            }

            var t1 = head
            var t2 = reverseList(slow)

            while (t1 != null && t2 != null) {
                if (t1.`val` != t2.`val`) {
                    return false
                }

                t1 = t1.next
                t2 = t2.next
            }

            return true
        }
    }

    expect {
        Solution().isPalindrome(null)
    }
}

