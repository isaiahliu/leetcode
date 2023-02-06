fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class Solution {
        fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            val root = ListNode(Int.MAX_VALUE)

            var current = root
            val lists = arrayOf(list1, list2)

            val indices = intArrayOf(0, 1)
            while (lists.any { it != null }) {
                val minIndex: Int = indices.minBy { lists[it]?.`val` ?: Int.MAX_VALUE }

                lists[minIndex]?.also {
                    current.next = it
                    current = it
                }

                lists[minIndex] = lists[minIndex]?.next
            }

            return root.next
        }
    }
}

