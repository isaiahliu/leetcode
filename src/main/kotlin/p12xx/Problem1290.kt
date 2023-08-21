package p12xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun getDecimalValue(head: ListNode?): Int {
            var result = 0

            var t = head
            while (t != null) {
                result *= 2
                result += t.`val`
                t = t.next
            }

            return result
        }
    }

    expect {
        Solution().getDecimalValue(
            null
        )
    }
}
