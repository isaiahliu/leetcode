package p03xx

import util.ListNode
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class Solution(head: ListNode?) {
        val list = arrayListOf<Int>()

        init {
            var t = head
            while (t != null) {
                list.add(t.`val`)
                t = t.next
            }
        }

        fun getRandom(): Int {
            return list[Random.nextInt(list.size)]
        }
    }

    measureTimeMillis {
        val r = Solution(null)

        r.getRandom().also { println(it) }
        r.getRandom().also { println(it) }
        r.getRandom().also { println(it) }
        r.getRandom().also { println(it) }
        r.getRandom().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

