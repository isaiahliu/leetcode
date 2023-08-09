package p17xx

import util.ListNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun swapNodes(head: ListNode?, k: Int): ListNode? {
            var cur = head ?: return null

            repeat(k - 1) {
                cur = cur.next ?: return null
            }

            val kNode = cur
            var newHead: ListNode = head

            while (newHead.next != null && cur.next != null) {
                newHead = newHead.next ?: break
                cur = cur.next ?: break
            }

            val kNode2 = newHead.takeIf { it.next != null } ?: cur

            val t = kNode.`val`
            kNode.`val` = kNode2.`val`
            kNode2.`val` = t

            return head
        }
    }

    measureTimeMillis {
        Solution().swapNodes(
            null, 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
