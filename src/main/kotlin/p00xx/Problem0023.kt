package p00xx

import util.ListNode
import java.util.*

fun main() {
    class Solution {
        fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            val root = ListNode(Int.MAX_VALUE)
            var current = root

            val queue = PriorityQueue<ListNode>(compareBy { it.`val` })
            lists.forEach {
                it?.also {
                    queue.offer(it)
                }
            }

            while (queue.isNotEmpty()) {
                val min = queue.poll()

                current.next = min

                min.next?.also {
                    queue.offer(it)
                }

                current = min
            }

            return root.next
        }
    }
}

