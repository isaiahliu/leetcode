package p03xx

import util.ListNode
import kotlin.random.Random
import util.expect

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

    expect {
        val r = Solution(null)

        r.getRandom()
        r.getRandom()
        r.getRandom()
        r.getRandom()
        r.getRandom()
    }
}

