package p32xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun modifiedList(nums: IntArray, head: ListNode?): ListNode? {
            val set = nums.toSet()

            val result = ListNode(-1, head)

            var prev: ListNode = result
            var cur = head
            while (cur != null) {
                if (cur.`val` in set) {
                    prev.next = cur.next
                } else {
                    prev = cur
                }

                cur = prev.next
            }

            return result.next
        }
    }

    expect {
        Solution().modifiedList(
            intArrayOf(1, 2, 3), ListNode(
                1, ListNode(
                    2, ListNode(
                        3, ListNode(
                            4, ListNode(
                                5
                            )
                        )
                    )
                )
            )
        )
    }
}
