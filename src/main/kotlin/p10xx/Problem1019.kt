package p10xx

import util.ListNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nextLargerNodes(head: ListNode?): IntArray {
            val list = arrayListOf<Int>()
            var t = head
            while (t != null) {
                list.add(t.`val`)

                t = t.next
            }

            val result = list.toIntArray()

            val queue = LinkedList<Int>()
            for (i in result.lastIndex downTo 0) {
                val num = result[i]

                while (true) {
                    if ((queue.peekFirst() ?: break) <= num) {
                        queue.poll()
                    } else {
                        break
                    }
                }

                result[i] = queue.peekFirst() ?: 0

                queue.push(num)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().nextLargerNodes(
            ListNode(2, ListNode(1, ListNode(5)))
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}