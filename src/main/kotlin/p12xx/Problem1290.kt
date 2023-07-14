package p12xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getDecimalValue(head: ListNode?): Int {
            var result = 0

            var t = head
            while (t != null) {
                result *= 2
                result += t.`val`
                t = t.next
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getDecimalValue(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
