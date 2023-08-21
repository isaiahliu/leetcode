package p09xx

import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.sign
import util.expect

fun main() {
    class Solution {
        fun canReorderDoubled(arr: IntArray): Boolean {
            val secondHalf = LinkedList<Int>()

            arr.sortedWith(compareBy<Int> { it.absoluteValue }.thenBy { it.sign }).forEach {
                if (secondHalf.peek() == it) {
                    secondHalf.pop()
                } else {
                    secondHalf.add(it * 2)
                }
            }

            return secondHalf.isEmpty()
        }
    }

    expect {
        Solution().canReorderDoubled(
            intArrayOf(4, -2, 2, -4)
        )
    }
}
