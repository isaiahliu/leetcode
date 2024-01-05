package p28xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun insertGreatestCommonDivisors(head: ListNode?): ListNode? {
            fun gcd(a: Int, b: Int): Int {
                return if (b == 0) a else gcd(b, a % b)
            }

            var current: ListNode? = head ?: return null

            while (current?.next != null) {
                val gcd = ListNode(gcd(current.`val`, current.next?.`val` ?: 0))

                gcd.next = current.next
                current.next = gcd
                current = gcd.next
            }

            return head
        }
    }

    expect {
        Solution().insertGreatestCommonDivisors(
            null
        )
    }
}
