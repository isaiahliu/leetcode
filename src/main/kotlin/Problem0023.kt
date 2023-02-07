fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
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

