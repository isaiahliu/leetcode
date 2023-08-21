package p16xx

import util.ListNode
import util.expect

fun main() {
    class Solution {
        fun mergeInBetween(list1: ListNode?, a: Int, b: Int, list2: ListNode?): ListNode? {
            var list2Tail = list2
            while (true) {
                list2Tail?.next?.also {
                    list2Tail = it
                } ?: break
            }

            var pos = 0
            var cur = list1

            while (true) {
                ++pos

                if (pos == a) {
                    cur?.next?.also {
                        cur?.next = list2
                        cur = it
                    }
                } else {
                    cur = cur?.next
                }

                if (pos == b) {
                    list2Tail?.next = cur?.next
                    break
                }
            }

            return list1
        }
    }

    expect {
        Solution().mergeInBetween(
            null, 1, 2, null
        )
    }
}

