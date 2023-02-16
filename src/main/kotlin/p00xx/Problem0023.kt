package p00xx

import util.ListNode

fun main() {
    class Solution {
        fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            val root = ListNode(Int.MAX_VALUE)
            var current = root

            val remainingIndices =
                lists.mapIndexedNotNull { index, listNode -> index.takeIf { listNode != null } }.toMutableList()

            while (remainingIndices.isNotEmpty()) {
                val minIndex = remainingIndices.minBy { lists[it]?.`val` ?: Int.MAX_VALUE }

                lists[minIndex]?.also {
                    current.next = it
                    current = it
                }

                lists[minIndex] = lists[minIndex]?.next

                if (lists[minIndex] == null) {
                    remainingIndices -= minIndex
                }
            }

            return root.next
        }
    }
}

