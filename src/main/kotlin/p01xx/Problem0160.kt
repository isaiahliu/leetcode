package p01xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
            var tA = headA
            var tB = headB

            var m = 0
            var n = 0
            while (tA != null || tB != null) {
                if (tA == tB) {
                    return tA
                }

                if (tA != null) {
                    m++
                }

                if (tB != null) {
                    n++
                }

                tA = tA?.next
                tB = tB?.next
            }

            tA = headA
            tB = headB

            repeat(m - n) {
                tA = tA?.next
            }

            repeat(n - m) {
                tB = tB?.next
            }

            while (tA != null && tB != null) {
                if (tA == tB) {
                    return tA
                }

                tA = tA?.next
                tB = tB?.next
            }

            return null
        }
    }

    expect {
        Solution().getIntersectionNode(
            null, null
        )
    }
}

