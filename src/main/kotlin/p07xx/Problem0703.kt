package p07xx

import java.util.*
import util.expect

fun main() {
    class KthLargest(val k: Int, nums: IntArray) {
        val head = PriorityQueue<Int>()
        fun add(`val`: Int): Int {
            head.offer(`val`)
            if (head.size > k) {
                head.poll()
            }

            return head.peek()
        }

        init {
            nums.forEach {
                add(it)
            }
        }
    }

    expect {
        KthLargest(1, intArrayOf(1, 2, 3, 4, 5)).add(
            1
        )
    }
}