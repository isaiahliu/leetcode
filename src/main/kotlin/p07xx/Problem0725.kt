package p07xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun splitListToParts(head: ListNode?, k: Int): Array<ListNode?> {
            if (k == 1) {
                return arrayOf(head)
            }

            var t: ListNode? = head
            val prevs = Array(k) {
                t.also { t = t?.next }
            }

            if (t != null) {
                while (prevs[prevs.lastIndex]?.next != null) {
                    var round = 0
                    while (prevs[prevs.lastIndex]?.next != null && round < k) {
                        for (i in round until k) {
                            prevs[i]?.next?.also { prevs[i] = it }
                        }

                        round++
                    }
                }
            }

            for (i in k - 1 downTo 1) {
                prevs[i] = prevs[i - 1]?.next
                prevs[i - 1]?.next = null
            }

            prevs[0] = head

            return prevs
        }
    }

    measureTimeMillis {
        Solution().splitListToParts(
            ListNode(
                1, ListNode(
                    2, ListNode(
                        3, ListNode(4, ListNode(5, ListNode(6, ListNode(7, ListNode(8, ListNode(9, ListNode(10)))))))
                    )
                )
            ), 3
        ).map { it?.`val` }.also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}