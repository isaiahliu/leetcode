package p00xx

fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class Solution {
        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            var t1 = l1
            var t2 = l2

            var additional = 0

            val root = ListNode(-1)
            var current = root

            while (t1 != null || t2 != null) {
                val sum = (t1?.`val` ?: 0) + (t2?.`val` ?: 0) + additional

                additional = sum / 10

                current = ListNode(sum % 10).also { current.next = it }

                t1 = t1?.next
                t2 = t2?.next
            }

            if (additional > 0) {
                current.next = ListNode(additional)
            }

            return root.next
        }
    }
}

