package p09xx

import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.sign
import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().canReorderDoubled(
            intArrayOf(4, -2, 2, -4)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
