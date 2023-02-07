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
        fun swapPairs(head: ListNode?): ListNode? {
            return head?.next?.let {
                head.next = swapPairs(it.next)
                it.next = head
                it
            } ?: head
        }
    }
}

