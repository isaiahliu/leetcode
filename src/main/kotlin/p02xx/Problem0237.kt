package p02xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun deleteNode(node: ListNode?) {
            node?.`val` = node?.next?.`val` ?: 0
            node?.next = node?.next?.next
        }
    }

    measureTimeMillis {
        Solution().deleteNode(null).also { println(it) }
    }
}

