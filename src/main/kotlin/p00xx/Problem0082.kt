package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class Solution {
        fun deleteDuplicates(head: ListNode?): ListNode? {
            val root = ListNode(Int.MAX_VALUE)
            root.next = head

            var t: ListNode? = root
            while (t?.next != null) {
                t.next?.also {
                    val currentValue = it.`val`
                    var next = it.next
                    val nextValue = next?.`val`

                    if (currentValue == nextValue) {
                        while (next?.`val` == currentValue) {
                            next = next.next
                        }

                        t?.next = next
                    } else {
                        t = it
                    }
                }
            }

            return root.next
        }
    }

    measureTimeMillis {
        Solution().deleteDuplicates(null)
    }.also { println("Time cost: ${it}ms") }
}

