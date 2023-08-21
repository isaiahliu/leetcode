package p02xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun deleteNode(node: ListNode?) {
            node?.`val` = node?.next?.`val` ?: 0
            node?.next = node?.next?.next
        }
    }

    expect {
        Solution().deleteNode(null)
    }
}

