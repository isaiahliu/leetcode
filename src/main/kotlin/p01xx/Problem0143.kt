package p01xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun reorderList(head: ListNode?): Unit {
            fun ListNode.walk(): ListNode {
                this.next?.next ?: return this

                var pre = this
                var cur = this.next
                while (cur?.next != null) {
                    pre = cur
                    cur = cur.next
                }

                val next = this.next

                pre.next = null
                this.next = cur
                cur?.next = next?.walk()

                return this
            }

            head?.walk()
        }
    }

    expect {
        Solution().reorderList(
            null
        )
    }
}

