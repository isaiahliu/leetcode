package p21xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun mergeNodes(head: ListNode?): ListNode? {
            val root = ListNode(0)
            var writeNode = root

            var sum = 0

            var current = head
            while (current != null) {
                current.`val`.takeIf { it > 0 }?.also {
                    sum += it
                } ?: run {
                    if (sum > 0) {
                        ListNode(sum).also {
                            writeNode.next = it
                            writeNode = it
                        }
                        sum = 0
                    }
                }

                current = current.next
            }

            return root.next
        }
    }

    expect {
        Solution().mergeNodes(
            null
        )
    }
}