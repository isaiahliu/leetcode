package p12xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun getDecimalValue(head: ListNode?): Int {
            return generateSequence(head) { it.next }.fold(0) { acc, n -> acc * 2 + n.`val` }
        }
    }

    expect {
        Solution().getDecimalValue(
            null
        )
    }
}
