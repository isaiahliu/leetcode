package p20xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
            var firstCriticalIndex = -1
            var criticalIndex = -1

            var min = Int.MAX_VALUE

            var pre = head?.`val` ?: 0
            var cur = head

            var index = 0
            while (cur != null) {
                val value = cur.`val`
                if (value > pre && cur.next?.takeIf { it.`val` < value } != null || value < pre && cur.next?.takeIf { it.`val` > value } != null) {
                    if (criticalIndex >= 0) {
                        min = min.coerceAtMost(index - criticalIndex)
                    }
                    criticalIndex = index

                    if (firstCriticalIndex < 0) {
                        firstCriticalIndex = criticalIndex
                    }
                }

                pre = value
                cur = cur.next
                index++
            }

            return (criticalIndex - firstCriticalIndex).takeIf { it > 0 }?.let {
                intArrayOf(min, it)
            } ?: intArrayOf(-1, -1)
        }
    }

    expect {
        Solution().nodesBetweenCriticalPoints(
            null
        )
    }
}